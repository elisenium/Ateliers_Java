import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {
    private TodoList todoList;
    private Task task;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
        task = new Task("task 1", "wash the dishes");
    }

    @Test
    void addTask() {
        assertAll(
                () -> assertTrue(todoList.addTask(task)),
                () -> assertTrue(todoList.containsTask(task))
        );
    }

    @Test
    void addEmptyTask() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    task = new Task("", "");
                }),

                () -> assertThrows(IllegalArgumentException.class, () -> {
                    task = new Task(null, "send files");
                }),

                () -> assertThrows(IllegalArgumentException.class, () -> {
                    task = new Task("school", null);
                })
        );
    }

    @Test
    void addExistingTask() {
        todoList.addTask(task);
        assertFalse(todoList.addTask(task));
    }

    @Test
    void removeTask() {
        todoList.addTask(task);
        assertTrue(todoList.removeTask(task));
        assertFalse(todoList.containsTask(task));
    }

    @Test
    void removeUnexistingTask() {
        Task nonExistentTask = new Task("task 2", "clean the house");
        assertFalse(todoList.removeTask(nonExistentTask));
    }

    @Test
    void completeTask() {
        todoList.addTask(task);
        assertTrue(todoList.completeTask(task));
        assertTrue(task.isCompleted());
    }

    @Test
    void updateTaskTitle() {
        todoList.addTask(task);
        Task updatedTask = new Task("new task 1", "wash the dishes");
        assertTrue(todoList.updateTask(task, updatedTask));
        assertTrue(todoList.containsTask(updatedTask));
        assertFalse(todoList.containsTask(task));
    }

    @Test
    void updateTaskDescription() {
        todoList.addTask(task);
        Task updatedTask = new Task("task 1", "clean the house");
        assertTrue(todoList.updateTask(task, updatedTask));
        assertTrue(todoList.containsTask(updatedTask));
        assertFalse(todoList.containsTask(task));
    }

    @Test
    void getTask() {
        todoList.addTask(task);
        Task retrievedTask = todoList.getTask("task 1", "wash the dishes");
        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
    }

    @Test
    void updateTodoList() {
        todoList.addTask(task);
        Task newTask = new Task("new task 1", "clean the house");
        assertTrue(todoList.updateTask(task, newTask));
        assertTrue(todoList.containsTask(newTask));
        assertFalse(todoList.containsTask(task));
    }


}