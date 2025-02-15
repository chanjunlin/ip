package chin.util;

import java.util.ArrayList;

import chin.storage.Storage;
import chin.task.Deadline;
import chin.task.Event;
import chin.task.Task;
import chin.task.TaskType;

/**
 * Custom list class to manage the collection of tasks
 */
public class CustomList {

    private final ArrayList<Task> customTaskList;
    private static final String STRING_INFO = "Oki, I add this task for you:\n ";
    private Storage storage;

    /**
     * Create a new custom list with the file path to write to
     */
    public CustomList(String userInput) {
        customTaskList = new ArrayList<>();
    }

    /**
     * Adding a new task to the list
     *
     * @param task The task to be added
     */
    public void addToList(Task task) {
        customTaskList.add(task);
    }

    /**
     * Get the current number of tasks in the list
     *
     * @return The number of tasks in the list
     */
    public int size() {
        return customTaskList.size();
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
     *
     * @return The string containing all the tasks in the list
     */
    public String showList() {
        StringBuilder returnString = new StringBuilder();
        if (customTaskList.isEmpty()) { // if ArrayList is empty, let the user know
            returnString.append("List empty la");
        } else { // else show everything in the list
            returnString.append("Here are the tasks in your list: ").append("\n");
            for (int i = 0; i < customTaskList.size(); i++) {
                returnString.append((i + 1)).append(". ").append(customTaskList.get(i).show()).append("\n");
            }
        }
        return returnString.toString();
    }

    /**
     * Marks a specific task by index
     *
     * @param index The index number of task to be marked
     * @throws ChinChinException If the list is empty or if the specified index is out of bounds
     */
    public String markTask(int index) throws ChinChinException {
        StringBuilder returnString;
        try {
            returnString = new StringBuilder();
            index -= 1;
            Task currentTask = customTaskList.get(index);
            boolean isMarked = currentTask.isDone();
            if (!isMarked) {
                currentTask.mark();
                updateList();
                returnString.append("Orh, marked the task as done liao!");
                returnString.append(currentTask.show());
            } else {
                returnString.append("Marked already. You mean unmark ah?");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException("er.. check again! The list not that long.");
        }
        return returnString.toString();
    }

    /**
     * Unmarks a specific task by index
     *
     * @param index The index number of task to be unmarked
     * @throws ChinChinException If the list is empty or if the specified index is out of bounds
     */
    public String unmarkTask(int index) throws ChinChinException {
        StringBuilder returnString;
        try {
            returnString = new StringBuilder();
            index -= 1;
            Task currentTask = customTaskList.get(index);
            boolean isMarked = currentTask.isDone();
            if (isMarked) {
                currentTask.unmark();
                updateList();
                returnString.append("Orh, marked the task as undone liao!");
                returnString.append(currentTask.show());
            } else {
                returnString.append("Not even marked. You mean mark ah?");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException("er.. check again! The list not that long.");
        }
        return returnString.toString();
    }

    /**
     * Calls createTodoTask to create a new TODO task
     *
     * @param userInput The user's input
     * @return The task description
     * @throws ChinChinException If the task description is empty
     */
    public String todoTask(String userInput) throws ChinChinException {
        String taskInfo = STRING_INFO;
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
            throw new ChinChinException("your 'todo' task got no description le");
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
        String taskInfo = STRING_INFO;
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
        String taskInfo = STRING_INFO;
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
        String eventString = "event ";
        String eventDesc = getString(userInput, eventString);

        int fromIndex = userInput.indexOf("/from ") + "/from ".length();
        int toIndex = userInput.indexOf("/to");
        String betweenFromAndTo = userInput.substring(fromIndex, toIndex).trim();

        String afterTo = userInput.substring(toIndex + "/to ".length()).trim();

        return new Event(eventDesc, TaskType.EVENT, betweenFromAndTo, afterTo, userInput);
    }

    /**
     * Returns a String from the user's Input
     *
     * @param userInput   The user's input
     * @param eventString The String "event "
     * @return The event's description
     * @throws ChinChinException If there is no event description, if there is no starting timing,
     *                           if there is no ending deadline
     */
    private static String getString(String userInput, String eventString) throws ChinChinException {
        int eventDescIndex = userInput.indexOf(eventString) + eventString.length();
        if (eventString.length() > userInput.length()) {
            throw new ChinChinException("bro your event task got no description");
        } else if (!userInput.contains("/from")) {
            throw new ChinChinException("if you don't state the starting, then just use 'deadline' feature");
        } else if (!userInput.contains("/to")) {
            throw new ChinChinException("no ending then isn't it the same as a normal task..");
        }

        return userInput.substring(eventDescIndex, userInput.indexOf("/from")).trim();
    }

    /**
     * Delete a specified task from the collection by its index
     *
     * @param index The index of the task to be deleted
     * @throws ChinChinException If the list is empty or if the specified index is out of bounds
     */
    public String deleteTask(int index) throws ChinChinException {
        try {
            index -= 1;
            Task currentTask = customTaskList.get(index);
            String taskInfo = currentTask.show();
            customTaskList.remove(index);
            updateList();
            return "Okay Boss, removed liao:\n" + taskInfo;
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException("er.. check again! The list not that long.");
        }
    }

    /**
     * Updates the list when there is any changes done to the list
     */
    public void updateList() throws ChinChinException {
        this.storage.updateList(customTaskList);
    }

    /**
     * Retrieve the task at the index
     *
     * @param index Index of the task
     * @return The tasks at the specific index
     */
    public Task getTask(int index) {
        Task retrievedTask = customTaskList.get(index);
        System.out.println(retrievedTask.show());
        return retrievedTask;
    }

    /**
     * Locate for the tasks containing the keyword
     *
     * @param keyword The keyword to search for
     * @return
     */
    public String findKeyword(String keyword) {
        StringBuilder returnString = new StringBuilder();
        boolean isEmpty = true;
        for (int i = 0; i < customTaskList.size(); i++) {
            String taskDescription = customTaskList.get(i).show();
            if (taskDescription.contains(keyword)) {
                if (isEmpty) {
                    returnString = new StringBuilder("Here's some of the matches: ");
                }
                returnString.append(i + 1).append(". ").append(taskDescription);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            return "No matches la..";
        } else {
            return returnString.toString();
        }
    }
}
