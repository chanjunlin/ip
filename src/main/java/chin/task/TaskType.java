package chin.task;

/**
 * Enumeration representing the different types of tasks.
 */
public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String tag;

    /**
     * Constructs a TaskType with the specified tag
     *
     * @param tag Gives the tag to the task
     */
    TaskType(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the tag of this task type.
     *
     * @return The tag associated with this TaskType.
     */
    public String getTag() {
        return this.tag;
    }
}
