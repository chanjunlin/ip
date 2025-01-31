/**
 * Represents a generic task with a completion status and a name.
 */
public class Task {
    protected boolean isDone = false;
    protected String taskName;
    protected TaskType taskTag;

    /**
     * Constructs a new Task object with specified name and type.
     *
     * @param name The description of the task.
     * @param type The type of the task (e.g. TODO, DEADLINE).
     */
    public Task(String task, TaskType taskTag) {
        this.taskName = task;
        this.taskTag = taskTag;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
        return;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
        return;
    }

    /**
     * Get the tag representing the type of this task.
     *
     * @return The tag associated with the task type.
     */
    public String getTag() {
        return this.taskTag.getTag();
    }

    /**
     * Display string representation of the Task's details including its status and name.
     *
     * @return A formatted string showing whether it's completed and its details.
     */
    public String show() {
        String header = (isDone ? "[X]" : "[ ]");
        return this.taskTag.getTag() + header + " " + this.taskName;
    }
}