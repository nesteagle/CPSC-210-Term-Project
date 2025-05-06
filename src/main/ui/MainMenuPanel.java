package ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

// Represents the main panel of the application
public class MainMenuPanel extends SidePanel {
    private JButton newButton;
    private JButton clearButton;

    // EFFECTS: creates an instance of this class
    public MainMenuPanel(Menu menu) {
        super(menu);
        createButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates button
    private void createButtons() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        newButton = new JButton(new NewEntryAction());
        this.add(newButton, constraints);

        constraints.gridy = 1;
        clearButton = new JButton(new ClearAllEntriesAction());
        this.add(clearButton, constraints);
    }

    // Represents an action to create a budget entry
    private class NewEntryAction extends AbstractAction {
        // EFFECTS: creates a new instance of this class
        public NewEntryAction() {
            super("New Entry");
        }

        // EFFECTS: handles action performed
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.makeNewEntry();
        }
    }

    // Represents an action to clear all budget entries
    private class ClearAllEntriesAction extends AbstractAction {
        // EFFECTS: creates a new instance of this class
        public ClearAllEntriesAction() {
            super("Clear All Entries");
        }

        // EFFECTS: handles action performed
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.clearAllEntries();
        }
    }

}
