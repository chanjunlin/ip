package chin.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chin.util.ChinChinException;
import chin.util.DateFormatter;

/**
 * Represent a DEADLINE task with a due date
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    /**
     * Constructs a Deadline object with a description and due date
     *
     * @param task      The task description
     * @param taskType  The type of Task, TaskType.DEADLINE
     * @param date      The input string containing the due date
     * @param userInput The user input to get the description of the task
     * @throws ChinChinException If there is an error parsing the provided date string into
     *                           the required format.
     */
    public Deadline(String task, TaskType taskType, String date, String userInput) throws ChinChinException {
        super(task, taskType, userInput);
        this.dueDate = parseDate(date);
    }

    /**
     * Display string representation of this deadline's details
     *
     * @return A reformatted string showing whether it's completed and its details including due date.
     */
    @Override
    public String show() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return super.show() + "\nDEADLINE: " + this.dueDate.format(displayFormatter);
    }

    public LocalDateTime getDeadline() {
        return this.dueDate;
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
                if (!format.toString().contains(" HHmm")) {
                    return LocalDate.parse(dateString.trim(), format).atStartOfDay();
                }
                return LocalDateTime.parse(dateString.trim(), format);
            } catch (Exception ignored) {
                // Continue trying other formats
            }
        }

        throw new ChinChinException("Can you please choose proper date format?");
    }

    @Override
    public boolean isScheduledOn(LocalDate targetDate) {
        return this.dueDate.toLocalDate().equals(targetDate);
    }

    @Override
    public String getType() {
        return "deadline";
    }


}
