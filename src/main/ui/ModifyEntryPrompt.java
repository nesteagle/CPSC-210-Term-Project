package ui;

import javax.swing.JButton;

public class ModifyEntryPrompt extends EntryPrompt {
    // EFFECTS: instantiates a new instance of this class
    public ModifyEntryPrompt(Menu menu) {
        super(menu);
    }

    // MODIFIES: this
    // EFFECTS: creates button
    @Override
    protected void createButton() {
        constraints.gridy = 4;
        button = new JButton(new ModifyEntryAction());
        this.add(button, constraints);
    }

    // Represents an action to create a budget entry
    private class ModifyEntryAction extends EntryAction {
        // EFFECTS: creates a new instance of this class
        public ModifyEntryAction() {
            super("Modify Entry");
        }

        // MODIFIES: menu
        // EFFECTS: replaces entry
        @Override
        protected void handleSuccess(String titleText, int goal, int amount) {
            menu.replaceEntry(titleText, goal, amount);
        }
    }
}
