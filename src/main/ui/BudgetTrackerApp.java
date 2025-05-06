// package ui;

// import model.BudgetEntry;
// import model.BudgetTracker;
// import persistence.JsonReader;
// import persistence.JsonWriter;

// import java.io.IOException;
// import java.util.Scanner;

// // The console functionality of the application.
// public class BudgetTrackerApp {
//     private BudgetTracker tracker;
//     private BudgetEntry selected;

//     private Scanner input;
//     private JsonReader reader;
//     private JsonWriter writer;
//     private static final String FILE_LOCATION = "./data/budgetTracker.json";

//     // EFFECTS: runs the budget tracker application
//     public BudgetTrackerApp() {
//         runTracker();
//     }

//     // MODIFIES: this
//     // EFFECTS: processes user input accordingly
//     private void runTracker() {
//         String command = null;
//         setUp();
//         while (true) {
//             displayCommands();
//             command = input.next();
//             command = command.toLowerCase();
//             if (command.equals("q")) {
//                 break;
//             } else {
//                 processCommand(command);
//             }
//         }
//         System.out.println("\n Application quit");
//     }

//     // MODIFIES: this
//     // EFFECTS: initializes fields
//     private void setUp() {
//         reader = new JsonReader(FILE_LOCATION);
//         writer = new JsonWriter(FILE_LOCATION);
//         tracker = new BudgetTracker();
//         input = new Scanner(System.in);
//         input.useDelimiter("\r?\n|\r");
//     }

//     // EFFECTS: displays command options to user
//     private void displayCommands() {
//         System.out.println("\n Commands:");
//         System.out.println("\t 'n': create a new budget entry");
//         System.out.println("\t 'v': view all budget entries");
//         System.out.println("\t 'e': selects and modifies a budget entry");
//         System.out.println("\t 's': saves budget tracker to file");
//         System.out.println("\t 'l': loads last saved budget tracker");
//         System.out.println("\t 'c': clears all budget entries");
//         System.out.println("\t 'q': quits application");
//     }

//     // EFFECTS: displays viewing-related command options to user
//     private void displaySelectCommands() {
//         System.out.println("\n Commands:");
//         System.out.println("\t 'm': modifies the selected budget entry");
//         System.out.println("\t 'r': removes selected budget entry from the tracker");
//         System.out.println("\t 'b': deselects budget entry and returns to main commands");
//     }

//     // MODIFIES: this, tracker
//     // EFFECTS: processes input.
//     private void processCommand(String input) {
//         switch (input) {
//             case "n":
//                 tracker.addEntry(processNewEntry());
//                 break;
//             case "v":
//                 displayAllEntries();
//                 break;
//             case "e":
//                 handleSelect();
//                 break;
//             case "s":
//                 saveToFile();
//                 break;
//             case "l":
//                 loadFromFile();
//                 break;
//             case "c":
//                 clearEntries();
//                 break;
//             default:
//                 System.out.println("\n Invalid command.");
//                 break;
//         }
//     }

//     // EFFECTS: prompts user to confirm action
//     private boolean processConfirm() {
//         System.out.println("\n Type 'c' to confirm or 'b' to cancel");
//         while (true) {
//             String command = input.next();
//             switch (command) {
//                 case "c":
//                     return true;
//                 case "b":
//                     System.out.println("\n Cancelled.");
//                     return false;
//                 default:
//                     System.out.println("Invalid command, enter 'c' or 'b'.");
//                     break;
//             }
//         }
//     }

//     // EFFECTS: creates new entry
//     private BudgetEntry processNewEntry() {
//         String command;
//         String title;
//         int amount;
//         int goal;
//         System.out.println("\n Enter the entry's title.");
//         command = input.next();
//         while (command.isEmpty()) {
//             System.out.println("Invalid string, must be non-empty.");
//             command = input.next();
//         }
//         title = command;
//         System.out.println("\n Enter the entry's goal.");
//         goal = Integer.parseInt(numericCommand());
//         System.out.println("\n Enter the entry's amount fulfilled.");
//         amount = Integer.parseInt(numericCommand());
//         while (amount > goal) {
//             System.out.println("Amount may not exceed goal.");
//             amount = Integer.parseInt(numericCommand());
//         }
//         return new BudgetEntry(title, goal, amount);
//     }

//     // EFFECTS: returns if string is numeric and non-negative
//     private boolean isNumeric(String input) {
//         return input.matches("[0-9]+");
//     }

//     // EFFECTS: returns a numeric input command
//     private String numericCommand() {
//         String command;
//         command = input.next();
//         while (!isNumeric(command)) {
//             System.out.println("Invalid, must be a non-negative integer.");
//             command = input.next();
//         }
//         return command;
//     }

//     // MODIFIES: this
//     // EFFECTS: processes selecting a BudgetEntry by index
//     private boolean processSelect() {
//         if (tracker.getEntries().isEmpty()) {
//             System.out.println("No entries, cannot select.");
//             return false;
//         }
//         System.out.println("\n Enter entry name to select.");
//         String command = input.next().toLowerCase();
//         for (BudgetEntry e : tracker.getEntries()) {
//             if (e.getTitle().toLowerCase().equals(command)) {
//                 selected = e;
//                 System.out.println("Selected!");
//                 return true;
//             }
//         }
//         System.out.println("Selection not found.");
//         return false;
//     }

//     // EFFECTS: displays all budget entries in tracker
//     private void displayAllEntries() {
//         if (tracker.getEntries().isEmpty()) {
//             System.out.println("No entries.");
//         }
//         for (int i = 0; i < tracker.getEntries().size(); i++) {
//             BudgetEntry entry = tracker.getEntries().get(i);
//             System.out.println("\nEntry " + (i + 1) + ":");
//             System.out.println("Title: " + entry.getTitle());
//             System.out.println("Current amount: " + entry.getAmount());
//             System.out.println("Goal: " + entry.getGoal());
//         }
//     }

//     // MODIFIES: this, tracker
//     // EFFECTS: processes commands on a selected entry
//     private void processSelectCommands() {
//         while (true) {
//             String command = input.next();
//             switch (command) {
//                 case "m":
//                     tracker.removeEntry(selected);
//                     selected = processNewEntry();
//                     tracker.addEntry(selected);
//                     return;
//                 case "r":
//                     if (processConfirm()) {
//                         tracker.removeEntry(selected);
//                         System.out.println("Removed selected entry!");
//                     }
//                     return;
//                 case "b":
//                     selected = null;
//                     System.out.println("\n Deselected, returning to menu.");
//                     return;
//                 default:
//                     System.out.println("Invalid command, please enter 'm' 'r' or 'b'.");
//                     break;
//             }
//         }
//     }

//     // EFFECTS: processes and displays select commands
//     private void handleSelect() {
//         if (processSelect()) {
//             displaySelectCommands();
//             processSelectCommands();
//         }
//     }

//     // MODIFIES: writer
//     // EFFECTS: saves budget tracker to file
//     private void saveToFile() {
//         System.out.println("Saving current data to file. Are you sure?");
//         if (processConfirm()) {
//             try {
//                 writer.write(tracker);
//                 System.out.println("Saved to file!");
//             } catch (IOException e) {
//                 System.out.println("Unable to save to file.");
//             }
//         }
//     }

//     // MODIFIES: this
//     // EFFECTS: loads budget tracker from file
//     private void loadFromFile() {
//         System.out.println("Loading data from file, will overwrite current data. Are you sure?");
//         if (processConfirm()) {
//             try {
//                 tracker = reader.read();
//                 System.out.println("Loaded from file!");
//             } catch (IOException e) {
//                 System.out.println("Unable to load from file.");
//             }
//         }
//     }

//     // MODIFIES: this, tracker
//     // EFFECTS: clears all entries from tracker
//     private void clearEntries() {
//         if (processConfirm()) {
//             tracker.clearAllEntries();
//             System.out.println("Cleared all entries!");
//         }
//     }
// }
