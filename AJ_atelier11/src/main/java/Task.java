public class Task {
    private String title;
    private String description;
    private boolean completed;

    public Task(String title, String description) {
        if (title == null || description == null || title.equals("") || description.equals(""))
            throw new IllegalArgumentException();

        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        if (!this.isCompleted())
            this.title = title;
    }

    public void setDescription(String description) {
        if (!this.isCompleted())
            this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
