public class Deadline extends Task {
    private String dueDate;

    public Deadline(String task, TaskType taskType, String date) {
        super(task, taskType);
        String[] correctedDate = date.split(" ", 2);
        this.dueDate = correctedDate[1];
    }

    public String show() {
        return super.show() + "(by: " + this.dueDate + ")";
    }
}