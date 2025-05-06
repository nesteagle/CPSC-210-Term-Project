package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.BudgetTracker;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

// The GUI functionality of the application
public class BudgetTrackerAppGUI extends JFrame implements ActionListener {
    private CardLayout layout;
    private JPanel menus;
    private BudgetTracker tracker;
    private JsonReader reader;
    private JsonWriter writer;
    private Menu menu;
    private static final String FILE_LOCATION = "./data/budgetTracker.json";
    private static final String ICON_LOCATION = "./icons/SaveIcon.png";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // EFFECTS: runs the GUI budget tracker application
    public BudgetTrackerAppGUI() {
        tracker = new BudgetTracker();
        reader = new JsonReader(FILE_LOCATION);
        writer = new JsonWriter(FILE_LOCATION);
        initGUI();
    }

    // MODIFIES: this
    // EFFECTS: sets up UI fields
    private void initGUI() {
        this.setSize(WIDTH, HEIGHT);

        layout = new CardLayout();
        menus = new JPanel(layout);
        menu = new Menu(tracker);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Term Project: Budget Tracker");
        menus.add(menu, "MAIN");

        createDropDownMenu();
        this.add(menus);
        this.addWindowListener(new WindowCloseListener());
        layout.show(menus, "MAIN");

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates drop down menu for saving and loading
    private void createDropDownMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Save", new ImageIcon(ICON_LOCATION));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Load", new ImageIcon(ICON_LOCATION));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        this.setJMenuBar(menuBar);
    }

    // EFFECTS: handles action performed by menu and keyboard shortcuts
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem eventSource = (JMenuItem) e.getSource();
        if (eventSource.getText().equals("Save")) {
            saveToFile();
        } else {
            loadFromFile();
        }
    }

    // MODIFIES: writer
    // EFFECTS: saves budget tracker to file
    private void saveToFile() {
        try {
            writer.write(tracker);
        } catch (IOException e) {
            return;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads budget tracker from file
    private void loadFromFile() {
        try {
            tracker = reader.read();
            menu.displayEntries(tracker);
        } catch (IOException e) {
            return;
        }
    }

    private class WindowCloseListener extends WindowAdapter {
        // EFFECTS: handles window close
        @Override
        public void windowClosing(WindowEvent e) {
            printLog();
        }

        // EFFECTS: prints event log
        private void printLog() {
            for (Event e : EventLog.getInstance()) {
                System.out.println(e.toString());
            }
        }
    }
}
