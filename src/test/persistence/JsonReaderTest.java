package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    private JsonReader reader;

    @Test
    void testReadNonExistentFile() {
        reader = new JsonReader("./data/nonExistentFile.json");
        try {
            reader.read();
            fail(); // intended to fail
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReadNonEmptyFile() {
        reader = new JsonReader("./data/exampleBudgetTracker.json");
        try {
            BudgetTracker tracker = reader.read();
            List<BudgetEntry> entries = tracker.getEntries();
            assertEquals(3, entries.size());
            checkEntry(entries.get(0), "Savings", 500, 50);
            checkEntry(entries.get(1), "Groceries", 200, 150);
            checkEntry(entries.get(2), "Misc", 350, 0);
        } catch (IOException e) {
            fail(); // expect success
        }
    }

    @Test
    void testReadEmptyFile() {
        reader = new JsonReader("./data/emptyBudgetTracker.json");
        try {
            BudgetTracker tracker = reader.read();
            List<BudgetEntry> entries = tracker.getEntries();
            assertEquals(0, entries.size());
        } catch (IOException e) {
            fail(); // expect success
        }
    }

    void checkEntry(BudgetEntry e, String title, int goal, int amount) {
        assertEquals(e.getTitle(), title);
        assertEquals(e.getGoal(), goal);
        assertEquals(e.getAmount(), amount);
    }
}
