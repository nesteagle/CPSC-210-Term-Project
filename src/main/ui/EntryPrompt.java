package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// Represents an prompt menu with input fields to create a budget entry
public abstract class EntryPrompt extends SidePanel {
    protected JTextField titleField;
    protected JTextField amountField;
    protected JTextField goalField;
    protected JTextArea outputField;
    protected JButton button;
    protected static Dimension PREFERRED = new Dimension(300, 40);
    protected static String DEFAULT = "Enter a title, amount, and goal. Amount and goal must be non-negative integers.";

    // EFFECTS: Creates an instance of this class with a state and empty input
    // fields
    public EntryPrompt(Menu menu) {
        super(menu);
        createFields();
        createOutputField();
        createButton();
    }

    // MODIFIES: this
    // EFFECTS: sets up fields
    protected void createFields() {
        JLabel titleFieldText = new JLabel("Title:");
        JLabel amountFieldText = new JLabel("Amount:");
        JLabel goalFieldText = new JLabel("Goal:");
        titleField = new JTextField(12);
        goalField = new JTextField(12);
        amountField = new JTextField(12);
        JTextField[] fields = { titleField, amountField, goalField };
        JLabel[] labels = { titleFieldText, amountFieldText, goalFieldText };
        for (int i = 0; i < fields.length; i++) {
            constraints.gridy = i;
            fields[i].setPreferredSize(PREFERRED);
            labels[i].setLabelFor(fields[i]);
            constraints.gridx = 0;
            constraints.weightx = 0;
            this.add(labels[i], constraints);
            constraints.gridx = 1;
            constraints.weightx = 1;
            this.add(fields[i], constraints);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates output field
    protected void createOutputField() {
        constraints.gridy = 3;
        constraints.weightx = 0;

        outputField = new JTextArea();
        outputField.setText(DEFAULT);
        outputField.setEditable(false);
        outputField.setWrapStyleWord(true);
        outputField.setLineWrap(true);
        outputField.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
        outputField.setPreferredSize(new Dimension(300, 80));
        this.add(outputField, constraints);
    }

    // MODIFIES: this
    // EFFECTS: creates button
    protected abstract void createButton();

    // EFFECTS: returns if string is numeric and non-negative
    protected boolean isNumeric(String input) {
        return input.matches("[0-9]+");
    }

    // MODIFIES: this
    // EFFECTS: sets output field text to message
    protected void setOutput(String message) {
        outputField.setText(message);
    }

    // MODIFIES: this
    // EFFECTS: resets fields
    public void reset() {
        titleField.setText("");
        amountField.setText("");
        goalField.setText("");

        outputField.setText(DEFAULT);
    }

    protected abstract class EntryAction extends AbstractAction {
        // EFFECTS: creates a new instance of this class
        public EntryAction(String name) {
            super(name);
        }

        // MODIFIES: EntryPrompt
        // EFFECTS: handles action performed
        @Override
        public void actionPerformed(ActionEvent e) {
            String titleText = titleField.getText();
            String goalText = goalField.getText();
            String amountText = amountField.getText();
            if (titleText == null) {
                setOutput("Please enter a title.");
            } else if (!isNumeric(amountText) || !isNumeric(goalText)) {
                setOutput("Goal and amount must be non-negative integers.");
            } else {
                int goal = Integer.parseInt(goalText);
                int amount = Integer.parseInt(amountText);
                if (goal < 0 || amount < 0) {
                    setOutput("Goal and amount must be non-negative integers.");
                } else if (goal == 0) {
                    setOutput("Please enter a valid goal.");
                } else if (amount > goal) {
                    setOutput("Please set an goal greater than your amount.");
                } else {
                    handleSuccess(titleText, goal, amount);
                }
            }
        }

        // MODIFIES: menu
        // EFFECTS: replaces entry
        protected abstract void handleSuccess(String titleText, int goal, int amount);
    }
}
