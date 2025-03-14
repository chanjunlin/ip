package chin.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chin.util.ChinChinException;
import chin.util.DateFormatter;

/**
 * Represents an event task with starting and ending date
 */
public class Event extends Task {
    private static final String CONTAINS_TIME = "Value(HourOfDay,2)Value(MinuteOfHour,2)";

    private LocalDateTime startingDate;
    private LocalDateTime endingDate;


    /**
     * Constructs an Event object with a description, starting and ending
     *
     * @param task      The task description
     * @param taskType  The type of task, TaskType.EVENT
     * @param starting  The starting of the event
     * @param ending    The ending of the event
     * @param userInput The user input to get the description of the task
     */
    public Event(String task, TaskType taskType, String starting, String ending, String userInput)
            throws ChinChinException {
        super(task, taskType, userInput);
        dateFormatter(starting, ending);
    }

    /**
     * Display string representation of this event's details
     *
     * @return A reformatted string showing whether it's completed and its details including starting and ending
     */
    @Override
    public String show() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return super.show() + " (FROM: " + this.startingDate.format(displayFormatter) + " TO: "
            + this.endingDate.format(displayFormatter) + ")";
    }

    /**
     * Return True if the current dueDate is the same as the targetDate
     *
     * @param targetDate The LocalDate to check against.
     * @return  True if the LocalDate matches the targetDate else, false.
     */
    @Override
    public boolean isScheduledOn(LocalDate targetDate) {
        return (targetDate.isEqual(startingDate.toLocalDate()) || targetDate.isAfter(startingDate.toLocalDate()))
            && (targetDate.isEqual(endingDate.toLocalDate()) || targetDate.isBefore(endingDate.toLocalDate()));
    }

    public LocalDateTime getStarting() {
        return this.startingDate;
    }

    public LocalDateTime getEnding() {
        return this.endingDate;
    }

    /**
     * Parses and formats the starting and ending date strings into a standardized date format.
     *
     * @param starting The string representing the starting date that needs to be parsed.
     * @param ending   The string representing the ending date that needs to be parsed.
     * @throws ChinChinException If either of the provided date strings cannot be parsed
     *                           into the required format.
     */
    public void dateFormatter(String starting, String ending) throws ChinChinException {
        try {
            this.startingDate = parseDate(starting);
            this.endingDate = parseDate(ending);
        } catch (Exception e) {
            throw new ChinChinException("error... " + e.getMessage());
        }
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
                if (format.toString().contains(CONTAINS_TIME)) {
                    return LocalDateTime.parse(dateString.trim(), format);
                } else {
                    return LocalDate.parse(dateString.trim(), format).atStartOfDay();
                }
            } catch (Exception ignored) {
                // Continue trying other formats
            }
        }
        throw new ChinChinException("Can you please choose proper date format?");
    }

    @Override
    public String getType() {
        return "event";
    }
}
