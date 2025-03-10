package chin.util;

import java.time.LocalDate;
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
    private static final String STRING_INFO = "Oki, task added liao ✅:\n";
    private static final String STRING_NOT_LONG = "er.. check again! The list not that long.";

    private ArrayList<Task> customTaskList;
    private Storage storage;

    /**
     * Create a new custom list with the file path to write to
     */
    public CustomList(String filePath) {
        customTaskList = new ArrayList<>();
    }

    /**
     * Adding a new task to the list
     *
     * @param task The task to be added
     */
    public void addToList(Task task) {
        customTaskList.add(task);
        sortTasks();
    }

    /**
     * Sorts the tasks in customList based on priority and additional criteria
     *      for deadlines: the deadline with the earlier deadline has a higher priority
     *      for events: the event with the earlier starting date has a higher priority, if they are the same, then
     *      the event with the earlier ending date has a higher priority.
     */
    private void sortTasks() {
        customTaskList.sort((task1, task2) -> {
            int priorityComparison = Integer.compare(getPriority(task1), getPriority(task2));

            if (priorityComparison == 0) {
                if (task1.getType().equals("deadline") && task2.getType().equals("deadline")) {
                    return compareDeadlines((Deadline) task1, (Deadline) task2);
                } else if (task1.getType().equals("event") && task2.getType().equals("event")) {
                    return compareEvents((Event) task1, (Event) task2);
                }
            }
            return priorityComparison;
        });
    }

    /**
     * Compares two deadline tasks based on their due dates.
     *
     * @param deadline1 The first deadline task to be compared.
     * @param deadline2 The second deadline task to be compared.
     * @return A negative integer if deadline1's due date is earlier than
     *         deadline2's due date; zero if they are equal or one/both deadlines
     *         are null; a positive integer if deadline1's due date is later than
     *         deadline2's due date.
     */
    private int compareDeadlines(Deadline deadline1, Deadline deadline2) {
        if (deadline1.getDeadline() != null && deadline2.getDeadline() != null) {
            return deadline1.getDeadline().compareTo(deadline2.getDeadline());
        }
        return 0;
    }

    /**
     * Compares two event tasks based on their starting and ending times.
     *
     * @param event1 The first event task to be compared.
     * @param event2 The second event task to be compared.
     * @return A negative integer if event1 starts earlier than event2, or if they
     *         start at the same time but event1 ends earlier than event2. Returns zero
     *         if both starting and ending times are equal. Returns a positive integer if event1
     *         starts later than event2, or ends later when both start at the same time.
     */
    private int compareEvents(Event event1, Event event2) {
        int startComparison = event1.getStarting().compareTo(event2.getStarting());

        if (startComparison != 0) {
            return startComparison;
        }

        return event1.getEnding().compareTo(event2.getEnding());
    }

    /**
     * Determines the priority of a task based on its type.
     *
     * @param task The task whose type will be used to determine its priority.
     * @return An integer representing the priority of the given task. Lower numbers indicate higher priorities.
     */
    private int getPriority(Task task) {
        return switch (task.getType()) {
        case "todo" -> 1;
        case "deadline" -> 2;
        case "event" -> 3;
        default -> Integer.MAX_VALUE;
        };
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
        return "Here are the tasks in your list: " + "\n\n" + getTodoList() + "\n" + getDeadlineList()
            + "\n" + getEventList();

    }

    /**
     * Retrieve the tasks of certain tag
     *
     * @param taskTag The tag required
     * @return The list of tasks that corresponds to the certain tag
     */
    public ArrayList<Task> filterTaskByTag(String taskTag) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : this.customTaskList) {
            if (task.getTag().equals(taskTag)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Retrieves a formatted list of tasks labeled as "Todo".
     *
     * @return A string representing a formatted list of Todo tasks,
     *      or an empty-list scenario message if no Todo tasks exist.
     */
    public String getTodoList() {
        ArrayList<Task> todoList = filterTaskByTag("[T]");
        StringBuilder todoString = new StringBuilder();

        int maxWidth = String.valueOf(todoList.size()).length();
        int todoIndex = 1;

        for (Task task : todoList) {
            int paddedIndex = getPadding(maxWidth, String.valueOf(todoIndex).length());
            todoString.append(todoIndex).append(".").append(" ".repeat(paddedIndex + 1)).append(task.show())
                .append("\n");
            todoIndex++;
        }

        if (todoString.isEmpty()) {
            todoString.append(emptyListScenario("task"));
        }

        return "[Todo \uD83D\uDCDD]:\n" + todoString + "\n";
    }

    /**
     * Retrieves a formatted list of tasks labeled as "Deadline".
     *
     * @return A string representing a formatted list of Deadline tasks,
     *      or an empty-list scenario message if no Deadline tasks exist.
     */
    public String getDeadlineList() {
        ArrayList<Task> deadlineList = filterTaskByTag("[D]");
        ArrayList<Task> todoList = filterTaskByTag("[T]");
        StringBuilder deadlineString = new StringBuilder();
        int maxWidth = String.valueOf(deadlineList.size()).length();
        int deadlineIndex = todoList.size() + 1;

        for (Task task : deadlineList) {
            int paddedIndex = getPadding(maxWidth, String.valueOf(deadlineIndex).length());
            deadlineString.append(deadlineIndex).append(".").append(" ".repeat(paddedIndex + 1)).append(task.show())
                .append("\n");
            deadlineIndex++;
        }

        if (deadlineString.isEmpty()) {
            deadlineString.append(emptyListScenario("deadline"));
        }

        return "[Deadline ⏰]:\n" + deadlineString + "\n";
    }

    /**
     * Retrieves a formatted list of tasks labeled as "Event".
     *
     * @return A string representing a formatted list of Event tasks,
     *      or an empty-list scenario message if no Event tasks exist.
     */
    public String getEventList() {
        ArrayList<Task> todoList = filterTaskByTag("[T]");
        ArrayList<Task> deadlineList = filterTaskByTag("[D]");
        ArrayList<Task> eventList = filterTaskByTag("[E]");

        StringBuilder eventString = new StringBuilder();
        int maxWidth = String.valueOf(eventList.size()).length();
        int eventIndex = todoList.size() + deadlineList.size() + 1;

        for (Task task : eventList) {
            int paddedIndex = getPadding(maxWidth, String.valueOf(eventIndex).length());
            eventString.append(eventIndex).append(".").append(" ".repeat(paddedIndex + 1)).append(task.show())
                .append("\n");
            eventIndex++;
        }

        if (eventString.isEmpty()) {
            eventString.append(emptyListScenario("event"));
        }

        return "[Event \uD83D\uDCC5]:\n" + eventString;
    }

    /**
     * Calculates the padding needed to align indices in a formatted list.
     *
     * @param maxWidth   The maximum width (number of digits) across all indices.
     * @param indexWidth The width (number of digits) of the current index.
     * @return An integer representing the number of spaces needed for padding.
     */
    public int getPadding(int maxWidth, int indexWidth) {
        return maxWidth - indexWidth;
    }

    /**
     * Returns a message indicating that there are no tasks of a specific type.
     *
     * @param type A string representing the type of task.
     * @return A string containing an empty-list message for the specified task type.
     */
    public String emptyListScenario(String type) {
        return String.format("No upcoming %s le", type);
    }

    /**
     * Generates a summary report of all tasks categorized by type.
     *
     * @return A string containing a human-readable summary report,
     *      including counts for each task category.
     */
    public String getSummary() {
        int totalTodos = filterTaskByTag("[T]").size();
        int totalDeadlines = filterTaskByTag("[D]").size();
        int totalEvents = filterTaskByTag("[E]").size();

        return """
            Here’s your summary:
            📝 Todos      : %d
            ⏰ Deadlines   : %d
            📅 Events     : %d
            """.formatted(totalTodos, totalDeadlines, totalEvents);
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
                returnString.append("Orh, marked the task as done liao!\n");
                returnString.append(currentTask.show());
            } else {
                returnString.append("Marked already. You mean unmark ah?");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException(STRING_NOT_LONG);
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
                returnString.append("Orh, marked the task as undone liao!\n");
                returnString.append(currentTask.show());
            } else {
                returnString.append("Not even marked. You mean mark ah?");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException(STRING_NOT_LONG);
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
        String todoString = "todo ";
        int todoDescIndex = userInput.indexOf(todoString);
        String todoDesc = userInput.substring(todoDescIndex + (todoString.length())).trim();
        if ((todoString.length()) >= userInput.length()) {
            throw new ChinChinException("your task description is empty bro..");
        }
        return new Task(todoDesc, TaskType.TODO, userInput);
    }

    /**
     * Processes user input to create a new DEADLINE task.
     *
     * @param userInput The user's input string containing the task description and deadline.
     * @return A String representing the description of the newly created task.
     * @throws ChinChinException If the user input is missing either:
     *                           - A valid task description
     *                           - A valid deadline (indicated by "/by").
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
     * Creates a new DEADLINE task based on user input.
     *
     * @param userInput The user's input string containing "deadline", followed by a description,
     *                  and ending with "/by due date".
     * @return A Deadline object representing the parsed DEADLINE task.
     * @throws ChinChinException If any of these conditions occur:
     *                           The user did not use "/by".
     *                           The user provided an empty task description.
     */
    public static Deadline createDeadlineTask(String userInput) throws ChinChinException {
        String deadlineDesc = getDeadlineString(userInput);

        int byIndex = userInput.indexOf("/by");
        int endByIndex = byIndex + "/by ".length();

        try {
            String afterBy = userInput.substring(endByIndex).trim();

            return new Deadline(deadlineDesc, TaskType.DEADLINE, afterBy, userInput);
        } catch (ChinChinException e) {
            throw new ChinChinException("Can you please choose proper date format?");
        } catch (Exception e) {
            throw new ChinChinException("jialat.. Please remember to key in your dates after '/by' hor, thanks");
        }
    }

    /**
     * Extracts the task description for a DEADLINE task from the user's input string.
     *
     * @param userInput The user's input string containing "deadline", followed by a
     *                  description, and ending with "/by due date".
     * @return A string representing only the description part of the DEADLINE task.
     * @throws ChinChinException If any of these conditions occur:
     *                           The user provided an empty or invalid description after "deadline".
     *                           The user did not use "/by"
     */
    private static String getDeadlineString(String userInput) throws ChinChinException {
        String deadlineString = "deadline ";
        int deadlineDescIndex = userInput.indexOf(deadlineString) + deadlineString.length();

        if (deadlineString.length() >= userInput.length()) {
            throw new ChinChinException("why is your task description empty?");
        } else if (!userInput.contains("/by")) {
            throw new ChinChinException("you never put deadline then use the deadline feature for what??");
        }
        return userInput.substring(deadlineDescIndex, userInput.indexOf("/by")).trim();
    }

    /**
     * Calls createEventTask to create an EVENT task
     *
     * @param userInput The user's input containing "Event", followed by a task description,
     *                  "/from date" and ending with "/by date".
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
        String eventDesc = getEventString(userInput, eventString);

        int fromIndex = userInput.indexOf("/from ") + "/from ".length();
        int toIndex = userInput.indexOf("/to");
        String betweenFromAndTo = userInput.substring(fromIndex, toIndex).trim();
        try {
            String afterTo = userInput.substring(toIndex + "/to ".length()).trim();

            return new Event(eventDesc, TaskType.EVENT, betweenFromAndTo, afterTo, userInput);
        } catch (ChinChinException e) {
            throw new ChinChinException("Can you please choose proper date format?");
        } catch (Exception e) {
            throw new ChinChinException("jialat.. Please remember to key in your dates after '/from' and '/to' hor, "
                + "thanks");
        }
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
    private static String getEventString(String userInput, String eventString) throws ChinChinException {
        int eventDescIndex = userInput.indexOf(eventString) + eventString.length();

        if (eventString.length() >= userInput.length()) {
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
     *
     * @throws ChinChinException When there's an error updating the list
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
        return customTaskList.get(index);
    }

    /**
     * Searches the task list for tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A String containing all matching tasks, each listed with its index,
     *      or a message stating "No matches la.." if no tasks match the keyword.
     */
    public String findKeyword(String keyword) {
        StringBuilder returnString = new StringBuilder();
        boolean isEmpty = true;
        for (int i = 0; i < customTaskList.size(); i++) {
            String taskDescription = customTaskList.get(i).show();
            if (taskDescription.contains(keyword)) {
                if (isEmpty) {
                    returnString = new StringBuilder("Here's some of the matches:\n");
                }
                returnString.append(i + 1).append(". ").append(taskDescription).append("\n");
                isEmpty = false;
            }
        }
        if (isEmpty) {
            return "No matches la..";
        } else {
            return returnString.toString();
        }
    }

    /**
     * Filters all the tasks by the date
     *
     * @param targetDate The specified date
     * @return The ArrayList of the tasks on the specified date
     */
    public ArrayList<Task> filterTasksByDate(LocalDate targetDate) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : customTaskList) {
            if (task.isScheduledOn(LocalDate.from(targetDate))) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }

}

