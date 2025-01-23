public class Deadline extends Task {
    private String dueDate;

    public Deadline(String task, String date) {
        super(task);
        String[] correctedDate = date.split(" ", 2);
        this.dueDate = correctedDate[1];
        this.taskTag = "[D]";
    }

    public String show() {
        return super.show() + "(by: " + this.dueDate + ")";
    }
}