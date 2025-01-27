public class Task {
    protected boolean isDone = false;
    protected String taskName;
    protected TaskType taskTag;

    public Task(String task, TaskType taskTag) {
        this.taskName = task;
        this.taskTag = taskTag;
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
        return this.taskTag.getTag();
    }

    // return [X] if task is marked, [ ] if tasked is not marked
    public String show() {
        String header = (isDone ? "[X]" : "[ ]");
        return this.taskTag.getTag() + header + " " + this.taskName;
    }
}