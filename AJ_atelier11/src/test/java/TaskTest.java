import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("task 1", "wash the dishes");
    }

    @Test
    void complete() {
        task.setCompleted(true);
        assertTrue(task.isCompleted());
    }

    @Test
    void editTitle() {
        task.setTitle("edited task");
        assertEquals("edited task", task.getTitle());

        task.setCompleted(true);
        task.setTitle("important task");
        assertNotEquals("important task", task.getTitle());
    }

    @Test
    void editDescription() {
        task.setDescription("do the laundry");
        assertEquals("do the laundry", task.getDescription());

        task.setCompleted(true);
        task.setDescription("make pancakes");
        assertNotEquals("make pancakes", task.getDescription());
    }

    @Test
    void createTaskWithInvalidTitle() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Task("", "description")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Task(null, "description"))
        );
    }

    @Test
    void createTaskWithInvalidDescription() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Task("title", "")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Task("title", null))
        );
    }
}