public class Task {
    protected boolean isDone = false;
    protected String taskName;
    protected String taskTag = "[T]";

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

    // Get the Task's tag
    public String getTag() {
        return this.taskTag;
    }

    // return [X] if task is marked, [ ] if tasked is not marked
    public String show() {
        String header = (isDone ? "[X]" : "[ ]");
        return getTag() + header + " " + this.taskName;
    }
}