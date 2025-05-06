package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetEntryTest {
    private BudgetEntry entry;

    @BeforeEach
    void runBefore() {
        entry = new BudgetEntry("Entry", 100, 500);
    }

    @Test
    void testConstructor() {
        BudgetEntry movieNight = new BudgetEntry("Movie Night", 100, 500);
        assertEquals("Movie Night", movieNight.getTitle());
        assertEquals(500, movieNight.getAmount());
        assertEquals(100, movieNight.getGoal());

        BudgetEntry tuition = new BudgetEntry("Tuition", 8000, 1234);
        assertEquals("Tuition", tuition.getTitle());
        assertEquals(1234, tuition.getAmount());
        assertEquals(8000, tuition.getGoal());
    }

    @Test
    void testChangeTitle() {
        entry.changeTitle("New Title");
        assertEquals("New Title", entry.getTitle());
        entry.changeTitle("Title2");
        assertEquals("Title2", entry.getTitle());
    }

    @Test
    void testChangeAmount() {
        entry.changeAmount(123);
        assertEquals(123, entry.getAmount());
        entry.changeAmount(0);
        assertEquals(0, entry.getAmount());
    }

    @Test
    void testChangeGoal() {
        entry.changeGoal(1234);
        assertEquals(1234, entry.getGoal());
        entry.changeGoal(0);
        assertEquals(0, entry.getGoal());
    }
}