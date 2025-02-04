package ChinChin.util;

import ChinChin.storage.Storage;
import ChinChin.task.Deadline;
import ChinChin.task.Event;
import ChinChin.task.Task;
import ChinChin.task.TaskType;
import ChinChin.ui.ChinChinUI;

import java.util.ArrayList;

/**
 * Custom list class to manage the collection of tasks
 */
public class CustomList {

    private static ArrayList<Task> customList = new ArrayList<Task>();
    private static final String STRINGINFO = "Oki, I add this task for you:\n ";
    public Storage storage;
    private String FILEPATH;

    /**
     * Create a new custom list with the file path to write to
     *
     * @param filePath The file path to write to
     */
    public CustomList(String filePath) {
        FILEPATH = filePath;
    }

    /**
     * Adding a new task to the list
     *
     * @param task The task to be added
     */
    public void addToList(Task task) {
        customList.add(task);
    }

    /**
     * Get the current number of tasks in the list
     *
     * @return The number of tasks in the list
     */
    public int size() {
        return customList.size();
    }

    /**
     * Setting the storage for this custom list
     *
     * @param storage The storage for this custom list
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Display all tasks in the list with their index number
     */
    public void showList() {
        if (customList.size() == 0) { // if ArrayList is empty, let the user know
            ChinChinUI.printHeader("List empty la");
            ChinChinUI.showLine();
        } else { // else show everything in the list
            ChinChinUI.printHeader("Here are the tasks in your list:");
            for (int i = 0; i < customList.size(); i++) {
                ChinChinUI.printMisc((i + 1) + ". " + (String) customList.get(i).show());
            }
            ChinChinUI.printBottom();
        }
    }

    /**
     * Marks a specific task by index
     *
     * @param index The index number of task to be marked
     * @throws ChinChinException If the list is empty or if the specified index is out of bounds
     */
    public void markTask(int index) throws ChinChinException {
        try {
            index -= 1;
            Task currentTask = customList.get(index);
            boolean isMarked = currentTask.isDone();
            if (!isMarked) {
                currentTask.mark();
                updateList();
                String taskInfo = currentTask.show();
                ChinChinUI.printHeader("Orh, marked the task as done liao!");
                ChinChinUI.printMisc(taskInfo);
                ChinChinUI.printBottom();
            } else {
                ChinChinUI.printHeader("Marked already. You mean unmark ah?");
                ChinChinUI.printBottom();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException("er.. check again! The list not that long.");
        }
    }

    /**
     * Unmarks a specific task by index
     *
     * @param index The index number of task to be unmarked
     * @throws ChinChinException If the list is empty or if the specified index is out of bounds
     */
    public void unmarkTask(int index) throws ChinChinException {
        try {
            index -= 1;
            Task currentTask = customList.get(index);
            boolean isMarked = currentTask.isDone();
            if (isMarked) {
                currentTask.unmark();
                updateList();
                String taskInfo = currentTask.show();
                ChinChinUI.printHeader("Orh, marked the task as undone liao!");
                ChinChinUI.printMisc(taskInfo);
                ChinChinUI.printBottom();
            } else {
                ChinChinUI.printHeader("Not even marked. You mean mark ah?");
                ChinChinUI.printBottom();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException("er.. check again! The list not that long.");
        }
    }

    /**
     * Calls createTodoTask to create a new TODO task
     *
     * @param userInput The user's input
     * @return The task description
     * @throws ChinChinException If the task description is empty
     */
    public String todoTask(String userInput) throws ChinChinException {
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
     * @throws ChinChinException If the task description is empty
     */
    public static Task createTodoTask(String userInput) throws ChinChinException {
        int todoDescIndex = userInput.indexOf("todo");
        if ("todo ".length() > userInput.length()) {
            throw new ChinChinException("your \'todo\' task got no description le");
        }
        String todoDesc = userInput.substring(todoDescIndex + "todo ".length()).trim();
        return new Task(todoDesc, TaskType.TODO, userInput);
    }


    /**
     * Calls createDeadlineTask to create a new DEADLINE task
     *
     * @param userInput The user's input
     * @return The task description
     * @throws ChinChinException If the task description is empty or the deadline is missing
     */
    public String deadlineTask(String userInput) throws ChinChinException {
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
     * @throws ChinChinException If the task description is empty or the deadline is missing
     */
    public static Deadline createDeadlineTask(String userInput) throws ChinChinException {
        int deadlineDescIndex = userInput.indexOf("deadline ") + "deadline ".length();
        if ("deadline ".length() > userInput.length()) {
            throw new ChinChinException("why is your task description empty?");
        } else if (!userInput.contains("/by")) {
            throw new ChinChinException("you never put deadline then use the deadline feature for what??");
        }
        String deadlineDesc = userInput.substring(deadlineDescIndex, userInput.indexOf("/by")).trim();

        int byIndex = userInput.indexOf("/by");
        int endByIndex = byIndex + "/by ".length();

        if (endByIndex > userInput.length()) {
            throw new ChinChinException("put /by and no deadline abit...");
        }

        String afterBy = userInput.substring(endByIndex).trim();

        return new Deadline(deadlineDesc, TaskType.DEADLINE, afterBy, userInput);
    }


    /**
     * Calls createEventTask to create an EVENT task
     *
     * @param userInput The user's input
     * @return The task description
     * @throws ChinChinException If the task description is empty, the starting time is missing,
     *                           or the ending time is missing
     */
    public String eventTask(String userInput) throws ChinChinException {
        String taskInfo = STRINGINFO;
        Event eventTask = createEventTask(userInput);
        taskInfo += eventTask.show();
        addToList(eventTask);
        updateList();
        return taskInfo;
    }

    /**
     * Creates a new Event task
     *
     * @param userInput The user's input
     * @return A Event task's object
     * @throws ChinChinException If the task description is empty, the starting time is missing,
     *                           or the ending time is missing
     */
    public static Event createEventTask(String userInput) throws ChinChinException {
        int eventDescIndex = userInput.indexOf("event ") + "event ".length();
        if ("event ".length() > userInput.length()) {
            throw new ChinChinException("bro your event task got no description");
        } else if (!userInput.contains("/from")) {
            throw new ChinChinException("if you don\'t state the starting, then just use \'deadline\' feature");
        } else if (!userInput.contains("/to")) {
            throw new ChinChinException("no ending then isn\'t it the same as a normal task..");
        }

        String eventDesc = userInput.substring(eventDescIndex, userInput.indexOf("/from")).trim();

        int fromIndex = userInput.indexOf("/from ") + "/from ".length();
        int toIndex = userInput.indexOf("/to");
        String betweenFromAndTo = userInput.substring(fromIndex, toIndex).trim();

        String afterTo = userInput.substring(toIndex + "/to ".length()).trim();

        return new Event(eventDesc, TaskType.EVENT, betweenFromAndTo, afterTo, userInput);
    }

    /**
     * Delete a specified task from the collection by its index
     *
     * @param index The index of the task to be deleted
     * @throws ChinChinException If the list is empty or if the specified index is out of bounds
     */
    public void deleteTask(int index) throws ChinChinException {
        try {
            index -= 1;
            Task currentTask = customList.get(index);
            String taskInfo = currentTask.show();
            customList.remove(index);
            updateList();
            ChinChinUI.printHeader("Okay Boss, removed liao:");
            ChinChinUI.printMisc(taskInfo);
            ChinChinUI.printBottom();
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException("er.. check again! The list not that long.");
        }
    }

    /**
     * Updates the list when there is any changes done to the list
     */
    public void updateList() {
        this.storage.updateList(customList);
    }


    public Task getTask(int Index) {
        Task retrievedTask = this.customList.get(Index);
        System.out.println(retrievedTask.show());
        return retrievedTask;
    }

    public void findKeyword(String keyword) {
        boolean isEmpty = true;
        for (int i = 0; i < customList.size(); i++) {
            String taskDescription = customList.get(i).show();
            if (taskDescription.contains(keyword)) {
                if (isEmpty) {
                    ChinChinUI.printHeader("Here's some of the matches: ");
                }
                ChinChinUI.printMisc(i + 1 + ". " + taskDescription);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            ChinChinUI.printHeader("No matches la..");
        }
        ChinChinUI.printBottom();
    }
}