package chin.util;

/**
 * Custom class for handling exceptions
 */
public class ChinChinException extends Exception {
    private static String line = "-------------------------------------";

    /**
     * constructs a new ChinChinException with a formatted message
     *
     * @param message The error message to be included in this exception
     */
    public ChinChinException(String message) {
        super(formatMessage(message));
    }

    /**
     * To format the message
     *
     * @param message The message to be formatted
     * @return The formatted message
     */
    private static String formatMessage(String message) {
        return message;
    }
}
