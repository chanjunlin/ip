public class Event extends Task {
    private String starting;
    private String ending;

    public Event(String task, TaskType taskType, String starting, String ending) {
        super(task, taskType);
        String[] correctedStarting = starting.split(" ", 2);
        String[] correctedEnding = ending.split(" ", 2);
        this.starting = correctedStarting[1];
        this.ending = correctedEnding[1];
    }

    public String show() {
        return super.show() + "(from: " + this.starting + " to: " + this.ending + ")";
    }
}