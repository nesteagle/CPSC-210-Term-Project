package ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

// Represents a menu with options operating on a budget entry
public class SelectMenu extends JPopupMenu {
    private Menu menu;

    // EFFECTS: creates a new instance of this class
    public SelectMenu(Menu parent) {
        this.menu = parent;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton modifyButton = new JButton(new ChangeEntryAction());
        JButton removeButton = new JButton(new RemoveEntryAction());
        modifyButton.setAlignmentX(CENTER_ALIGNMENT);
        removeButton.setAlignmentX(CENTER_ALIGNMENT);

        this.add(modifyButton);
        this.add(removeButton);
    }

    // Represents an action to modify a budget entry
    private class ChangeEntryAction extends AbstractAction {
        // EFFECTS: creates a new instance of this class
        public ChangeEntryAction() {
            super("Modify");
        }

        // EFFECTS: handles action performed
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.modifyEntry();
            hideThis();
        }
    }

    // Represents an action to remove a budget entry
    private class RemoveEntryAction extends AbstractAction {
        // EFFECTS: creates a new instance of this class
        public RemoveEntryAction() {
            super("Remove");
        }

        // EFFECTS: handles action performed
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeEntry();
            hideThis();
        }
    }

    // MODIFIES: menu
    // EFFECTS: removes this from menu
    private void hideThis() {
        setVisible(false);
    }
}
