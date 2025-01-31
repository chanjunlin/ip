/**
 * Represent a DEADLINE task with a due date
 */
public class Deadline extends Task {
    private String dueDate;

    /**
     * Constructs a Deadline object with a description and duedate
     *
     * @param task The task description
     * @param taskType The type of Task, TaskType.DEADLINE
     * @param date The input string containing the due date
     */
    public Deadline(String task, TaskType taskType, String date) {
        super(task, taskType);
        String[] correctedDate = date.split(" ", 2);
        this.dueDate = correctedDate[1];
    }

    /**
     * Display string representation of this deadline's details
     *
     * @return A reformatted string showing wether it's completed and its details including due date.
     */
    public String show() {
        return super.show() + "(by: " + this.dueDate + ")";
    }
}