package model;

import org.json.JSONObject;

// A budget entry with a title, an goal, and a current amount fulfilled.
public class BudgetEntry {
    private int amount;
    private int goal;
    private String title;

    // REQUIRES: starting amount must be >=0, goal must be >= 0, title must not be
    // empty string, amount <= goal.
    // EFFECTS: Creates an instance of this class with a title, starting amount and
    // goal
    public BudgetEntry(String title, int goal, int amount) {
        this.title = title;
        this.goal = goal;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }

    public int getGoal() {
        return goal;
    }

    // REQUIRES: title must not be empty string
    // MODIFIES: this
    // EFFECTS: changes title to the provided title
    public void changeTitle(String title) {
        EventLog.getInstance().logEvent(new Event("Changed entry title from " + this.title + " to " + title));
        this.title = title;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: changes budget amount to the provided amount
    public void changeAmount(int amount) {
        EventLog.getInstance().logEvent(new Event("Changed entry title from " + this.amount + " to " + amount));
        this.amount = amount;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: changes goal amount to the provided amount
    public void changeGoal(int amount) {
        EventLog.getInstance().logEvent(new Event("Changed entry title from " + this.goal + " to " + amount));
        this.goal = amount;
    }

    // EFFECTS: returns the object represented in JSON data
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("title", this.title);
        object.put("goal", this.goal);
        object.put("amount", this.amount);
        return object;
    }
}
