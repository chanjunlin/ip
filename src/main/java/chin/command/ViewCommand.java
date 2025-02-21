package chin.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import chin.storage.Storage;
import chin.task.Deadline;
import chin.task.Event;
import chin.task.Task;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;
import chin.util.DateFormatter;

/**
 * Represent a command to display the tasks on the interested date.
 */
public class ViewCommand extends ChinChinCommand {

    private final LocalDate viewDate;

    public ViewCommand(String userInput) throws ChinChinException {
        this.viewDate = LocalDate.from(parseDateFromInput(userInput));
    }

    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        ArrayList<Task> getTasksOnDate = taskList.filterTasksByDate(this.viewDate);

        return generateScheduleMessage(getTasksOnDate);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getcommandType() {
        return "view";
    }

    @Override
    public String displayHelpInfo() {
        return null;
    }

    public String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(displayFormatter);
    }

    /**
     * Filters out DeadlineTasks from the given list of tasks.
     *
     * @param tasks The list of all tasks.
     * @return A list containing only DeadlineTask objects.
     */
    private ArrayList<Deadline> getDeadlineTasks(ArrayList<Task> tasks) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline deadlineTask) {
                deadlineTasks.add(deadlineTask);
            }
        }
        return deadlineTasks;
    }

    /**
     * Filters out EventTasks from the given list of tasks.
     *
     * @param tasks The list of all tasks.
     * @return A list containing only EventTask objects.
     */
    private ArrayList<Event> getEventTasks(ArrayList<Task> tasks) {
        ArrayList<Event> eventTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Event eventTask) {
                eventTasks.add(eventTask);
            }
        }
        return eventTasks;
    }

    /**
     * Parses a date from the given user input string that contains a "/on" keyword.
     *
     * @param userInput The user input string containing text and a date specified after "/on".
     * @return A LocalDate object representing the parsed date from the user input.
     * @throws ChinChinException If:
     *                           The "/on" keyword is missing from the input.
     *                           No valid date follows the "/on" keyword.
     *                           The provided date does not match the "dd-MM-yyyy" format.
     */
    public LocalDate parseDateFromInput(String userInput) throws ChinChinException {
        if (!userInput.contains("/on")) {
            throw new ChinChinException("Use [/on] ah bro");
        }
        String[] parts = userInput.split("/on");
        if (parts.length < 2) {
            throw new ChinChinException("No date then how I know..");
        }

        try {
            String trimmedDate = parts[1].trim();
            for (DateTimeFormatter formatter : DateFormatter.DATEFORMAT) {
                try {
                    return LocalDate.parse(trimmedDate, formatter);
                } catch (DateTimeParseException e) {
                    // Continue trying other formats if this one fails
                }
            }
        } catch (Exception e) {
            throw new ChinChinException("Try using the proper format please.. If don't know use '[help date]'"
                + e.getMessage());
        }
        throw new ChinChinException("This is a magical error");
    }

    /**
     * Generates a schedule message based on filtered tasks.
     *
     * @param tasksForDay The list of filtered tasks scheduled on viewDate.
     * @return A formatted string showing all scheduled tasks or an appropriate message if none exist.
     */
    private String generateScheduleMessage(ArrayList<Task> tasksForDay) {
        if (tasksForDay.isEmpty()) {
            return "Got no scheduled tasks found for " + viewDate + ".";
        }

        ArrayList<Deadline> deadlineTasks = getDeadlineTasks(tasksForDay);
        ArrayList<Event> eventTasks = getEventTasks(tasksForDay);
        StringBuilder result = new StringBuilder("Schedule for ").append(viewDate).append(":\n");

        if (!deadlineTasks.isEmpty()) {
            result.append(formatDeadlines(deadlineTasks));
        }

        if (!eventTasks.isEmpty()) {
            result.append(formatEvents(eventTasks));
        }

        return result.toString();
    }

    /**
     * Formats a list of deadlines into a string section.
     *
     * @param deadlineTasks The list of DeadlineTask objects to format.
     * @return A formatted string representing the deadlines section.
     */
    private String formatDeadlines(ArrayList<Deadline> deadlineTasks) {
        StringBuilder result = new StringBuilder("\n[Deadlines]\n");

        int index = 1;
        for (Deadline deadline : deadlineTasks) {
            result.append(index++)
                .append(". ")
                .append(deadline.getTaskDescription())
                .append(" - Due at ")
                .append(deadline.getDeadline().toLocalTime())
                .append("\n");
        }

        return result.toString();
    }

    /**
     * Formats a list of deadlines into a string section.
     *
     * @param eventTasks The list of DeadlineTask objects to format.
     * @return A formatted string representing the deadlines section.
     */
    private String formatEvents(ArrayList<Event> eventTasks) {
        StringBuilder result = new StringBuilder("\n[Events]\n");

        int index = 1;
        for (Event event : eventTasks) {
            result.append(index++)
                .append(". ")
                .append(event.getTaskDescription())
                .append(" - from ")
                .append(getDateTimeString(event.getStarting()))
                .append(" to ")
                .append(getDateTimeString(event.getEnding()))
                .append("\n");
        }

        return result.toString();
    }


}
