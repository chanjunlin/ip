import java.util.ArrayList;

/**
 * Custom list class to manage the collection of tasks
 */
public class CustomList {

    // ArrayList of Task to keep track of tasks
    private ArrayList<Task> customList = new ArrayList<Task>();
    private final String STRINGINFO = "Oki, I add this task for you\n ";
    private Storage storage;

    public CustomList() {
        this.storage = new Storage();
    }

    /**
     * Adding a new task to the list
     *
     * @param task The task to be added
     */
    public void addToList(Task task) {
        this.customList.add(task);
    }

    /**
     * Get the current number of tasks in the list
     *
     * @return The number of tasks in the list
     */
    public int size() {
        return this.customList.size();
    }

    /**
     * Display all tasks in the list with their index number
     */
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

    /**
     * Marks a specific task by index
     *
     * @param index The index number of task to be marked
     */
    public void markTask(int index) {
        index -= 1;
        Task currentTask = this.customList.get(index);
        currentTask.mark();
        updateList();
        String taskInfo = currentTask.show();
        System.out.println("Orh, marked the task as done liao!");
        System.out.println(taskInfo);
    }

    /**
     * Unmarks a specific task by index
     *
     * @param index The index number of task to be unmarked
     */
    public void unmarkTask(int index) {
        index -= 1;
        Task currentTask = this.customList.get(index);
        currentTask.unmark();
        updateList();
        String taskInfo = currentTask.show();
        System.out.println("Orh, marked the task as undone!");
        System.out.println(taskInfo);
    }

    /**
     * Create a new TODO task
     *
     * @param userInput The task description
     * @return The task description
     */
    public String todoTask(String userInput) {
        String taskInfo = this.STRINGINFO;
        Task todoTask = new Task(userInput, TaskType.TODO);
        taskInfo += todoTask.show();
        addToList(todoTask);
        updateList();
        return taskInfo;
    }

    /**
     * Create a new DEADLINE task
     *
     * @param userInput The task description
     * @return The task description
     */
    public String deadlineTask(String userInput) {
        String taskInfo = this.STRINGINFO;
        String[] parts = userInput.split("/");
        Deadline deadlineTask = new Deadline(parts[0], TaskType.DEADLINE, parts[1]);
        taskInfo += deadlineTask.show();
        addToList(deadlineTask);
        updateList();
        return taskInfo;
    }

    /**
     * Create a new EVENT task
     *
     * @param userInput The task description
     * @return The task description
     */
    public String eventTask(String userInput) {
        String taskInfo = this.STRINGINFO;
        String[] parts = userInput.split("/");
        Event eventTask = new Event(parts[0], TaskType.EVENT, parts[1], parts[2]);
        taskInfo += eventTask.show();
        addToList(eventTask);
        updateList();
        return taskInfo;
    }

    /**
     * Delete a specified task from the collection by its index.
     *
     * @param index The index of the task to be deleted.
     * @return Information about deleted task or an error message if invalid index is provided.
     */
    public String deleteTask(int index) {
        index -= 1;
        Task currentTask = this.customList.get(index);
        String taskInfo = currentTask.show();
        this.customList.remove(index);
        updateList();
        return "Okay Boss, removed liao: \n " + taskInfo;
    }

    /**
     *  Updates the list when there is any changes done to the list
     */
    public void updateList() {
        storage.updateList(this.customList);
    }
}