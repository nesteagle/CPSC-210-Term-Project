package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTrackerTest {
    private BudgetTracker tracker;
    private BudgetEntry entryA;
    private BudgetEntry entryB;

    @BeforeEach
    void runBefore() {
        tracker = new BudgetTracker();
        entryA = new BudgetEntry("A", 10, 50);
        entryB = new BudgetEntry("B", 0, 200);
    }

    @Test
    void testConstructor() {
        tracker = new BudgetTracker();
        assertTrue(tracker.getEntries().isEmpty());

        BudgetTracker tracker1 = new BudgetTracker();
        assertTrue(tracker1.getEntries().isEmpty());
    }

    @Test
    void testAddEntry() {
        assertTrue(tracker.getEntries().isEmpty());

        tracker.addEntry(entryA);
        assertEquals(1, tracker.getEntries().size());
        assertEquals(entryA, tracker.getEntries().get(0));

        tracker.addEntry(entryB);
        assertEquals(2, tracker.getEntries().size());
        assertEquals(entryB, tracker.getEntries().get(1));
    }

    @Test
    void testRemoveEntry() {
        tracker.addEntry(entryB);
        tracker.addEntry(entryA);
        assertEquals(2, tracker.getEntries().size());

        tracker.removeEntry(entryB);
        assertEquals(1, tracker.getEntries().size());
        assertEquals(entryA, tracker.getEntries().get(0));

        tracker.removeEntry(entryA);
        assertTrue(tracker.getEntries().isEmpty());
    }

    @Test
    void testFindEntryWithTitle() {
        BudgetEntry entryA2 = new BudgetEntry("A", 23, 400);
        tracker.addEntry(entryB);
        tracker.addEntry(entryA);
        tracker.addEntry(entryA2);

        assertEquals(null, tracker.findEntryWithTitle("Tuition"));
        assertEquals(entryA, tracker.findEntryWithTitle("A"));
        assertEquals(entryB, tracker.findEntryWithTitle("B"));

        tracker.clearAllEntries();
        tracker.addEntry(entryA2);
        tracker.addEntry(entryA);
        assertEquals(entryA2, tracker.findEntryWithTitle("A"));
    }

    @Test
    void testClearAllEntries() {
        tracker.clearAllEntries();
        assertTrue(tracker.getEntries().isEmpty());

        tracker.addEntry(entryB);
        tracker.addEntry(entryA);
        assertEquals(2, tracker.getEntries().size());

        tracker.clearAllEntries();
        assertTrue(tracker.getEntries().isEmpty());
    }
}
