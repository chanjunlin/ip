import java.util.ArrayList;

public class CustomList {

    // ArrayList of Task to keep track of tasks
    private ArrayList<Task> customList = new ArrayList<Task>();

    public CustomList() {
    }

    // Add task to the ArrayList
    public void addToList(Task task) {
        this.customList.add(task);
    }

    // Returns the size of the list
    public int size() {
        return this.customList.size();
    }

    // Display the entire list
    public void showList() {
        if (this.customList.size() == 0) { // if ArrayList is empty, let the user know
            System.out.println("List is empty!");
        } else { // else show everything in the list
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.customList.size(); i++) {
                System.out.println((i + 1) + ". " + (String) this.customList.get(i).show());
            }
        }
    }

    // Mark the desired task
    public void markTask(int index) {
        index -= 1;
        Task currentTask = this.customList.get(index);
        currentTask.mark();
        String taskInfo = currentTask.show();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskInfo);
    }

    // Unmark the desired task
    public void unmarkTask(int index) {
        index -= 1;
        Task currentTask = this.customList.get(index);
        currentTask.unmark();
        String taskInfo = currentTask.show();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskInfo);
    }

    // Create a new Todo Task
    public String todoTask(String userInput) {
        String taskInfo = "Got it. I've added this task:\n  ";
        Task todoTask = new Task(userInput, TaskType.TODO);
        taskInfo += todoTask.show();
        addToList(todoTask);
        return taskInfo;
    }

    // Create a new Deadline Task
    public String deadlineTask(String userInput) {
        String taskInfo = "Got it. I've added this task:\n ";
        String[] parts = userInput.split("/");
        Deadline deadlineTask = new Deadline(parts[0], TaskType.DEADLINE, parts[1]);
        taskInfo += deadlineTask.show();
        addToList(deadlineTask);
        return taskInfo;
    }

    // Create a new Event Task
    public String eventTask(String userInput) {
        String taskInfo = "Got it. I've added this task:\n ";
        String[] parts = userInput.split("/");
        Event eventTask = new Event(parts[0], TaskType.EVENT, parts[1], parts[2]);
        taskInfo += eventTask.show();
        addToList(eventTask);
        return taskInfo;
    }

    // Delete specified task
    public String deleteTask(int index) {
        index -= 1;
        Task currentTask = this.customList.get(index);
        String taskInfo = currentTask.show();
        this.customList.remove(index);
        return "Okay Boss, removed liao: \n " + taskInfo;
    }
}