package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BudgetEntry;

// Represents a GUI element that displays a budget entry's title, amount, and goal
public class EntryDisplayBox extends JPanel {
    private BudgetEntry entry;
    private JLabel outputLabel;
    private static Font FONT = new Font("Default", Font.BOLD, 16);

    // REQUIRES: entry != null
    // EFFECTS: creates an instance of this class with a budget entry to display
    public EntryDisplayBox(BudgetEntry entry) {
        this.entry = entry;
        this.setPreferredSize(new Dimension(350, 100));
        this.setBackground(new Color(211, 211, 211));
        outputLabel = new JLabel(
                "Title: " + entry.getTitle() + ", Current amount: " + entry.getAmount() + ", Goal: " + entry.getGoal());
        outputLabel.setFont(FONT);
        this.add(outputLabel);
    }

    // EFFECTS: returns budget entry
    public BudgetEntry getEntry() {
        return this.entry;
    }
}
