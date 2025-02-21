package chin.task;

import java.time.LocalDate;

/**
 * Represents a generic task with a completion status and a name.
 */
public class Task {
    protected boolean isDone = false;
    protected String taskDescription;
    protected TaskType taskTag;
    protected String userInput;

    /**
     * Constructs a new Todo task object with a description
     *
     * @param task      The description of the task
     * @param taskTag   The type of the task (e.g. TODO, DEADLINE)
     * @param userInput The user input to get the description of this task
     */
    public Task(String task, TaskType taskTag, String userInput) {
        this.taskDescription = task;
        this.taskTag = taskTag;
        this.userInput = userInput;
    }

    /**
     * Marks the task as completed
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * To check if this task is marked as completed
     *
     * @return A boolean value depending on if the task is done or not
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Determines if this task is scheduled on a specific date.
     *
     * @param targetDate The LocalDate to check against.
     * @return true if this task occurs on the given date, false otherwise.
     */
    public boolean isScheduledOn(LocalDate targetDate) {
        return false;
    }

    /**
     * Get the tag representing the type of this task
     *
     * @return The tag associated with the task type
     */
    public String getTag() {
        return this.taskTag.getTag();
    }

    /**
     * Get the user input for this task
     *
     * @return A string containing the user's input
     */
    public String getUserInput() {
        return this.userInput;
    }

    /**
     * Display string representation of the Task's details including its status and name
     *
     * @return A formatted string showing whether it's completed and its details
     */
    public String show() {
        String header = (isDone ? "[✔️]" : "[ ]");
        return this.taskTag.getTag() + " " + header + " " + this.taskDescription;
    }

    /**
     * Retrieves the task's description
     *
     * @return A string returning the task's description
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    public String getType() {
        return "todo";
    }
}
