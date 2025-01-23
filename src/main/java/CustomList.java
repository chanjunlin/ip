import java.util.ArrayList;

public class CustomList {

    // ArrayList of Task to keep track of tasks
    private ArrayList<Task> customList = new ArrayList<Task>();

    public CustomList() {
    }

    // Add task to the ArrayList
    public void addToList(String task) {
        Task newTask = new Task(task);
        this.customList.add(newTask);
    }

    // Display the entire list
    public void showList() {
        if (this.customList.size() == 0) { // if ArrayList is empty, let the user know
            System.out.println("List is empty!");
        } else { // else show everything in the list
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
}