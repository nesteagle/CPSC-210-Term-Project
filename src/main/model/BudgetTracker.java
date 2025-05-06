package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

// A budget tracker with a list of budget entries.
public class BudgetTracker {
    private List<BudgetEntry> entries;

    // EFFECTS: Creates an instance of this class with no entries
    public BudgetTracker() {
        entries = new ArrayList<BudgetEntry>();
    }

    public List<BudgetEntry> getEntries() {
        return entries;
    }

    // MODIFIES: this
    // EFFECTS: Adds entry into current entries
    public void addEntry(BudgetEntry entry) {
        EventLog.getInstance().logEvent(new Event("Added entry " + entry.getTitle() + " with goal " + entry.getGoal()
                + " and amount " + entry.getAmount()));
        entries.add(entry);
    }

    // REQUIRES: entry must be in entries
    // MODIFIES: this
    // EFFECTS: removes entry from current entries
    public void removeEntry(BudgetEntry entry) {
        EventLog.getInstance().logEvent(new Event("Removed entry " + entry.getTitle()));
        entries.remove(entry);
    }

    // REQUIRES: title must not be empty string
    // EFFECTS: returns first BudgetEntry with given title if found, or null if not
    public BudgetEntry findEntryWithTitle(String title) {
        for (BudgetEntry e : entries) {
            if (e.getTitle().equals(title)) {
                return e;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: removes all budget entries from entries
    public void clearAllEntries() {
        EventLog.getInstance().logEvent(new Event("Cleared all entries"));
        entries.clear();
    }

    // EFFECTS: returns the object represented in JSON data
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonEntries = new JSONArray();

        for (BudgetEntry entry : entries) {
            jsonEntries.put(entry.toJson());
        }

        jsonObject.put("entries", jsonEntries);
        return jsonObject;
    }
}
