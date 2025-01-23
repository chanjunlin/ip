public class Task {
    private boolean isDone = false;
    private String taskName;

    public Task(String task) {
        this.taskName = task;
    }

    // Mark isDone -> true
    public void mark() {
        this.isDone = true;
        return;
    }

    // Mark isDone -> false
    public void unmark() {
        this.isDone = false;
        return;
    }

    // return [X] if task is marked, [ ] if tasked is not marked
    public String show() {
        String header = (isDone ? "[X]" : "[ ]");
        return header + " " + this.taskName;
    }
}