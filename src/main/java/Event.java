/**
 * Represents an event task with starting and ending date
 */
public class Event extends Task {
    private String starting;
    private String ending;

    /**
     * Constructs an Event object with a description, starting and ending
     *
     * @param task The task description
     * @param taskType The type of task, TaskType.EVENT
     * @param starting The starting of the event
     * @param ending The ending of the event
     */
    public Event(String task, TaskType taskType, String starting, String ending) {
        super(task, taskType);
        String[] correctedStarting = starting.split(" ", 2);
        String[] correctedEnding = ending.split(" ", 2);
        this.starting = correctedStarting[1];
        this.ending = correctedEnding[1];
    }

    /**
     * Display string representation of this event's details
     *
     * @return A reformatted string showing wether it's completed and its details including starting and ending
     */
    public String show() {
        return super.show() + "(from: " + this.starting + " to: " + this.ending + ")";
    }
}