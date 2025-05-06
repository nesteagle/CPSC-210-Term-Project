package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import model.BudgetTracker;
import model.Event;
import model.EventLog;

// A JSON writer: writes data to JSON file
public class JsonWriter {
    private FileWriter writer;
    private String destination;

    // EFFECTS: Creates an instance of this class with a destination file location
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: Writes tracker to destination file
    public void write(BudgetTracker tracker) throws IOException {
        writer = new FileWriter(new File(destination));
        JSONObject jsonObject = tracker.toJson();
        writer.write(jsonObject.toString(4)); // tab spacing
        EventLog.getInstance().logEvent(new Event("Saved entries to file!"));
        writer.close();
    }
}
