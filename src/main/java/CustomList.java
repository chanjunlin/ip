
import java.util.ArrayList;

/**
 * Custom list class to manage the collection of tasks
 */
public class CustomList {

    private static ArrayList<Task> customList = new ArrayList<Task>();
    private static final String STRINGINFO = "Oki, I add this task for you\n ";
    private static Storage storage;

    public CustomList() {
        storage = new Storage();
    }

    /**
     * Adding a new task to the list
     *
     * @param task The task to be added
     */
    public static void addToList(Task task) {
        customList.add(task);
    }

    /**
     * Get the current number of tasks in the list
     *
     * @return The number of tasks in the list
     */
    public static int size() {
        return customList.size();
    }

    /**
     * Display all tasks in the list with their index number
     */
    public static void showList() {
        if (customList.size() == 0) { // if ArrayList is empty, let the user know
            System.out.println("List empty la");
        } else { // else show everything in the list
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < customList.size(); i++) {
                System.out.println((i + 1) + ". " + (String) customList.get(i).show());
            }
        }
    }

    /**
     * Marks a specific task by index
     *
     * @param index The index number of task to be marked
     */
    public static void markTask(int index) {
        index -= 1;
        Task currentTask = customList.get(index);
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
    public static void unmarkTask(int index) {
        index -= 1;
        Task currentTask = customList.get(index);
        currentTask.unmark();
        updateList();
        String taskInfo = currentTask.show();
        System.out.println("Orh, marked the task as undone!");
        System.out.println(taskInfo);
    }

    /**
     * Calls createTodoTask to create a new TODO task
     *
     * @param userInput The user's input
     * @return The task description
     */
    public static String todoTask(String userInput) {
        String taskInfo = STRINGINFO;
        Task todoTask = createTodoTask(userInput);
        taskInfo += todoTask.show();
        addToList(todoTask);
        updateList();
        return taskInfo;
    }

    /**
     * Creates a new TODO task
     *
     * @param userInput The user's input
     * @return A Todo task's object
     */
    public static Task createTodoTask(String userInput) {
        int todoDescIndex = userInput.indexOf("todo");
        String todoDesc = userInput.substring(todoDescIndex + "todo ".length()).trim();

        return new Task(todoDesc, TaskType.TODO, userInput);
    }


    /**
     * Calls createDeadlineTask to create a new DEADLINE task
     *
     * @param userInput The user's input
     * @return The task description
     */
    public static String deadlineTask(String userInput) {
        String taskInfo = STRINGINFO;
        Deadline deadlineTask = createDeadlineTask(userInput);
        taskInfo += deadlineTask.show();
        addToList(deadlineTask);
        updateList();
        return taskInfo;
    }

    /**
     * Create a new DEADLINE task
     *
     * @param userInput The user's input
     * @return A Deadline task's object
     */
    public static Deadline createDeadlineTask(String userInput) {
        int deadlineDescIndex = userInput.indexOf("deadline ") + "deadline ".length();
        String deadlineDesc = userInput.substring(deadlineDescIndex, userInput.indexOf("/by")).trim();

        int byIndex = userInput.indexOf("/by");
        String afterBy = userInput.substring(byIndex + "/by ".length()).trim();

        return new Deadline(deadlineDesc, TaskType.DEADLINE, afterBy, userInput);
    }


    /**
     * Calls createEventTask to create an EVENT task
     *
     * @param userInput The user's input
     * @return The task description
     */
    public static String eventTask(String userInput) {
        String taskInfo = STRINGINFO;
        Event eventTask = createEventTask(userInput);
        taskInfo += eventTask.show();
        addToList(eventTask);
        updateList();
        return taskInfo;
    }

    /**
     * Creates a new EVENT task
     *
     * @param userInput The user's input
     * @return A Event task's object
     */
    public static Event createEventTask(String userInput) {
        int eventDescIndex = userInput.indexOf("event ") + "event ".length();
        String eventDesc = userInput.substring(eventDescIndex, userInput.indexOf("/from")).trim();

        int fromIndex = userInput.indexOf("/from ") + "/from ".length();
        int toIndex = userInput.indexOf("/to");
        String betweenFromAndTo = userInput.substring(fromIndex, toIndex).trim();

        String afterTo = userInput.substring(toIndex + "/to ".length()).trim();

        return new Event(eventDesc, TaskType.EVENT, betweenFromAndTo, afterTo, userInput);
    }

    /**
     * Delete a specified task from the collection by its index.
     *
     * @param index The index of the task to be deleted.
     * @return Information about deleted task or an error message if invalid index is provided.
     */
    public static String deleteTask(int index) {
        index -= 1;
        Task currentTask = customList.get(index);
        String taskInfo = currentTask.show();
        customList.remove(index);
        updateList();
        return "Okay Boss, removed liao: \n " + taskInfo;
    }

    /**
     * Updates the list when there is any changes done to the list
     */
    public static void updateList() {
        storage.updateList(customList);
    }

}