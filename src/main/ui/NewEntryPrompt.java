package ui;

import javax.swing.JButton;

import model.BudgetEntry;

public class NewEntryPrompt extends EntryPrompt {
    // EFFECTS: instantiates a new instance of this class
    public NewEntryPrompt(Menu menu) {
        super(menu);
    }

    // MODIFIES: this
    // EFFECTS: creates button
    @Override
    protected void createButton() {
        constraints.gridy = 4;
        button = new JButton(new CreateEntryAction());
        this.add(button, constraints);
    }

    // Represents an action to create a budget entry
    private class CreateEntryAction extends EntryAction {
        // EFFECTS: creates a new instance of this class
        public CreateEntryAction() {
            super("Create Entry");
        }

        // MODIFIES: menu
        // EFFECTS: replaces entry
        @Override
        protected void handleSuccess(String titleText, int goal, int amount) {
            BudgetEntry entry = new BudgetEntry(titleText, goal, amount);
            menu.addEntry(entry);
        }
    }
}
