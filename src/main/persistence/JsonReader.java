package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import model.BudgetEntry;
import model.BudgetTracker;
import model.EventLog;
import model.Event;

// A JSON reader: reads saved JSON files and loads data from them
public class JsonReader {
    private String fileLocation;

    // EFFECTS: Creates an instance of this class with a file location to read from
    public JsonReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // REQUIRES: JSON file must have correct syntax and contains no duplicate keys
    // EFFECTS: reads fileLocation and returns the corresponding tracker,
    // throws IOException if any errors occur
    public BudgetTracker read() throws IOException {
        String jsonContent = Files.readString(Paths.get(fileLocation));
        JSONObject jsonObject = new JSONObject(jsonContent);
        EventLog.getInstance().logEvent(new Event("Loaded entries from file!"));
        return createTrackerFromJson(jsonObject);
    }

    // MODIFIES: tracker
    // EFFECTS: parses JSONObject into a BudgetTracker
    private BudgetTracker createTrackerFromJson(JSONObject fileObject) {
        BudgetTracker tracker = new BudgetTracker();
        JSONArray entries = fileObject.getJSONArray("entries");

        for (int i = 0; i < entries.length(); i++) {
            JSONObject jsonObject = entries.getJSONObject(i);

            String title = jsonObject.getString("title");
            int goal = jsonObject.getInt("goal");
            int amount = jsonObject.getInt("amount");

            tracker.addEntry(new BudgetEntry(title, goal, amount));
        }
        return tracker;
    }
}