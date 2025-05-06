package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

// Represents a side panel GUI element
public abstract class SidePanel extends JPanel {
    protected Menu menu;
    protected GridBagConstraints constraints;

    // EFFECTS: creates a new instance of this class
    public SidePanel(Menu menu) {
        super(new GridBagLayout());
        this.menu = menu;

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }
}