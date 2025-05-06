package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.BudgetEntry;
import model.BudgetTracker;

// Represents the UI menu of the application
public class Menu extends JPanel {
    private BudgetTracker tracker;
    private JScrollPane scrollBar;
    private JPanel entryLog;
    private JPanel subMenus;
    private CardLayout layout;
    private NewEntryPrompt newEntryPromptPanel;
    private ModifyEntryPrompt modifyEntryPromptPanel;
    private MainMenuPanel mainMenuPanel;
    private SelectMenu selectMenu;
    private BudgetEntry selected;

    // EFFECTS: sets up a basic menu with no selected entry
    public Menu(BudgetTracker tracker) {
        selected = null;
        this.tracker = tracker;
        this.setLayout(new BorderLayout());
        layout = new CardLayout();
        subMenus = new JPanel(layout);

        createSubMenus();
        setMenuPanel();
        initScroll();
        this.setVisible(true);
    }

    private void createSubMenus() {
        mainMenuPanel = new MainMenuPanel(this);
        newEntryPromptPanel = new NewEntryPrompt(this);
        modifyEntryPromptPanel = new ModifyEntryPrompt(this);

        subMenus.add(newEntryPromptPanel, "NEW");
        subMenus.add(modifyEntryPromptPanel, "MODIFY");
        subMenus.add(mainMenuPanel, "MAIN");

        this.add(subMenus, BorderLayout.EAST);
        selectMenu = new SelectMenu(this);
        selectMenu.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: creates scroll bar
    private void initScroll() {
        entryLog = new JPanel();
        entryLog.setLayout(new BoxLayout(entryLog, BoxLayout.Y_AXIS));
        scrollBar = new JScrollPane(entryLog);

        add(scrollBar, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: displays menu panel
    private void setMenuPanel() {
        layout.show(subMenus, "MAIN");
        refresh();
    }

    // MODIFIES: this, entryPromptPanel
    // EFFECTS: resets and displays entry panel for creation
    private void setNewEntryPanel() {
        newEntryPromptPanel.reset();
        layout.show(subMenus, "NEW");
        refresh();
    }

    // MODIFIES: this, entryPromptPanel
    // EFFECTS: resets and displays entry panel for modification
    private void setModifyEntryPanel() {
        modifyEntryPromptPanel.reset();
        layout.show(subMenus, "MODIFY");
        refresh();
    }

    // EFFECTS: makes new entry panel
    public void makeNewEntry() {
        setNewEntryPanel();
    }

    // EFFECTS: makes new entry panel
    public void modifyEntry() {
        setModifyEntryPanel();
    }

    // MODIFIES: this, tracker
    // EFFECTS: adds entry and displays
    public void addEntry(BudgetEntry entry) {
        tracker.addEntry(entry);
        setMenuPanel();
        displayEntries(tracker);
    }

    // MODIFIES: this, selected
    // EFFECTS: replaces entry
    public void replaceEntry(String title, int goal, int amount) {
        selected.changeTitle(title);
        selected.changeGoal(goal);
        selected.changeAmount(amount);
        setMenuPanel();
        displayEntries(tracker);
    }

    public void removeEntry() {
        tracker.removeEntry(selected);
        setMenuPanel();
        displayEntries(tracker);
    }

    // MODIFIES: entry
    // EFFECTS: adds click handling to this panel
    private void addClickHandling(EntryDisplayBox entry) {
        entry.addMouseListener(new MouseAdapter() {
            // MODIFIES: this
            // EFFECTS: handles mouse click
            @Override
            public void mouseClicked(MouseEvent e) {
                openSelectOptions(entry);
            }
        });
    }

    // MODIFIES: this, selectMenu
    // EFFECTS: shows select menu
    private void openSelectOptions(EntryDisplayBox box) {
        selected = box.getEntry();
        selectMenu.setVisible(true);
        selectMenu.show(box, box.getWidth() + 20, box.getHeight() / 2 - 20);
    }

    // MODIFIES: this, tracker
    // EFFECTS: clears all entries from this and GUI
    public void clearAllEntries() {
        tracker.clearAllEntries();
        entryLog.removeAll();
        refresh();
    }

    // MODIFIES: this
    // EFFECTS: refreshes GUI elements
    private void refresh() {
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: adds entry logs to represent tracker state
    public void displayEntries(BudgetTracker tracker) {
        entryLog.removeAll();
        for (BudgetEntry e : tracker.getEntries()) {
            EntryDisplayBox entryPanel = new EntryDisplayBox(e);
            entryLog.add(entryPanel);
            entryLog.add(Box.createVerticalStrut(10));
            addClickHandling(entryPanel);
        }
        refresh();
    }
}
