/**
 * Custom class for handling exceptions
 */
public class ChinChinException extends Exception {
    private static String line = "-------------------------------------";

    public ChinChinException(String message) {
        super(formatMessage(message));
    }

    // A method to format the error message with the desired structure
    private static String formatMessage(String message) {
        return  "\n" + line +"\n"
                + " Eh wait! " + message + "\n"
                + line + "\n";
    }
}