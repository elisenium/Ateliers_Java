import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks = new ArrayList<Task>();

    public boolean addTask(Task task) {
        if (task.getTitle().isBlank() || task.getDescription().isBlank())
            throw new IllegalArgumentException();
        if (containsTask(task))
            return false;

        return tasks.add(task);
    }

    public boolean containsTask(Task task) {
        return tasks.contains(task);
    }

    public boolean removeTask(Task task) {
        if (!containsTask(task))
            return false;
        return tasks.remove(task);
    }

    public boolean completeTask(Task task) {
        if (!containsTask(task))
            return false;

        for (Task taskToComplete : tasks) {
            if (taskToComplete.equals(task)) {
                taskToComplete.setCompleted(true);
                return true;
            }
        }
        return false;
    }

    public Task getTask(String title, String description) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title) && task.getDescription().equals(description))
                return task;
        }
        return null;
    }

    public boolean updateTask(Task oldTask, Task newTask) {
        if (!containsTask(oldTask)) {
            return false;
        }
        if (newTask == null || newTask.getTitle() == null || newTask.getTitle().isBlank() || newTask.getDescription() == null) {
            throw new IllegalArgumentException("New task title and description cannot be null or blank");
        }
        int index = tasks.indexOf(oldTask);
        tasks.set(index, newTask);
        return true;
    }
}
