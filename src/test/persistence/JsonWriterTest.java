package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    private JsonWriter writer;
    private BudgetTracker toWrite;

    @BeforeEach
    void runBefore() {
        toWrite = new BudgetTracker();
        toWrite.addEntry(new BudgetEntry("Movies", 50, 5));
        toWrite.addEntry(new BudgetEntry("Rent", 200, 100));
    }

    @Test
    void testWriteIllegalFile() {
        try {
            BudgetTracker tracker = new BudgetTracker();
            writer = new JsonWriter("./data/\0fileWithNullByte.json");
            writer.write(tracker);
            fail();
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriteEmptyFile() {
        try {
            writer = new JsonWriter("./data/writerTestTracker.json");
            writer.write(new BudgetTracker());
            JsonReader reader = new JsonReader("./data/writerTestTracker.json");
            BudgetTracker tracker = reader.read();
            assertEquals(0, tracker.getEntries().size());
        } catch (IOException e) {
            fail(); // expect success
        }
    }

    @Test
    void testWriteNonEmptyFile() {
        try {
            writer = new JsonWriter("./data/writerTestTracker.json");
            writer.write(toWrite);
            JsonReader reader = new JsonReader("./data/writerTestTracker.json");
            BudgetTracker tracker = reader.read();
            List<BudgetEntry> entries = tracker.getEntries();
            assertEquals(2, entries.size());
            checkEntry(entries.get(0), "Movies", 50, 5);
            checkEntry(entries.get(1), "Rent", 200, 100);
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
