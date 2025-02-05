package chin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chin.util.ChinChinException;
import chin.util.DateFormatter;

/**
 * Represents an event task with starting and ending date
 */
public class Event extends Task {
    private LocalDateTime starting;
    private LocalDateTime ending;

    /**
     * Constructs an Event object with a description, starting and ending
     *
     * @param task      The task description
     * @param taskType  The type of task, TaskType.EVENT
     * @param starting  The starting of the event
     * @param ending    The ending of the event
     * @param userInput The user input to get the description of the task
     */
    public Event(String task, TaskType taskType, String starting, String ending, String userInput) {
        super(task, taskType, userInput);
        try {
            this.starting = parseDate(starting);
            this.ending = parseDate(ending);

        } catch (ChinChinException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Display string representation of this event's details
     *
     * @return A reformatted string showing wether it's completed and its details including starting and ending
     */
    @Override
    public String show() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return super.show() + " (from: " + this.starting.format(displayFormatter) + " to: "
                + this.ending.format(displayFormatter) + ")";
    }

    /**
     * Parses a date string into a LocalDateTime object based on supported date formats in DateFormatter
     *
     * @param dateString The input string to be parsed
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws ChinChinException If no matching format is found, indicating an unsupported or invalid format.
     */
    public LocalDateTime parseDate(String dateString) throws ChinChinException {
        for (DateTimeFormatter format : DateFormatter.DATEFORMAT) {
            try {
                return LocalDateTime.parse(dateString.trim(), format);
            } catch (DateTimeParseException ignored) {
                // ignored
            }
        }
        throw new ChinChinException("Can you please choose proper date format?");
    }
}
