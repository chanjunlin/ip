/**
 * Custom class for handling exceptions
 */
public class ChinChinException extends Exception {
    private static String line = "-------------------------------------";

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
        return "\n" + line + "\n"
                + " Eh wait! " + message + "\n"
                + line + "\n";
    }
}