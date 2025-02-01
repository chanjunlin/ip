import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a DEADLINE task with a due date
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline object with a description and duedate
     *
     * @param task      The task description
     * @param taskType  The type of Task, TaskType.DEADLINE
     * @param date      The input string containing the due date
     * @param userInput The user input to get the description of the task
     */
    public Deadline(String task, TaskType taskType, String date, String userInput) {
        super(task, taskType, userInput);
        try {
            this.dueDate = parseDate(date);
        } catch (ChinChinException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Display string representation of this deadline's details
     *
     * @return A reformatted string showing whether it's completed and its details including due date.
     */
    public String show() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return super.show() + " (by: " + this.dueDate.format(displayFormatter) + ")";
    }


    /**
     * Parses a date string into a LocalDateTime object based on supported date formats in DateFormatter
     *
     * @param DateString The input string to be parsed
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws ChinChinException If no matching format is found, indicating an unsupported or invalid format.
     */
    public LocalDateTime parseDate(String dateString) throws ChinChinException {
        for (DateTimeFormatter format : DateFormatter.DATEFORMAT) {
            try {
                return LocalDateTime.parse(dateString.trim(), format);
            } catch (DateTimeParseException ignored) {

            }
        }
        throw new ChinChinException("Can you please choose proper date format?");
    }
}