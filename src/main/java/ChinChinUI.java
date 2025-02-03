import java.util.Scanner;

/**
 * Handles all user interface interactions for the ChinChin list
 */
public class ChinChinUI {
    private static String LINE_SEPARATOR = "-------------------------------------";
    private Scanner scanner;

    /**
     * Constructs a new ChinChin instance
     */
    public ChinChinUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Print out a divider
     */
    public static void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Display bot's greeting message.
     */
    public static void displayGreeting() {
        String greetings = "Nihao, I'm ChinChin\nWhat you want?";
        System.out.println(LINE_SEPARATOR);
        System.out.println(greetings);
        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Display bot's farewell message.
     */
    public static void goodbye() {
        String goodbyes = "Bye. Hope to see you again soon!";
        System.out.println("\n" + LINE_SEPARATOR);
        System.out.println(goodbyes);
        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Print information about the task.
     *
     * @param taskInfo The information about the task being displayed.
     */
    public static void printInfo(String taskInfo, int size) {
        System.out.println("\n" + LINE_SEPARATOR);
        System.out.println(taskInfo);
        System.out.println("Now you got " + String.valueOf(size) + " tasks in the list.");
        System.out.println(LINE_SEPARATOR + "\n");
    }


    /**
     * Prints out the text for the user without formatting
     * @param text
     */
    public static void printMisc(String text) {
        System.out.println(text);
    }

    /**
     * Prints the text wrapped by the top border lines
     * @param text
     */
    public static void printHeader(String text) {
        System.out.println("\n" + LINE_SEPARATOR);
        System.out.println(text);
    }

    /**
     * prints the bottom border line
     */
    public static void printBottom() {
        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Read the user's input
     *
     * @return The user's input but removing any trailing spaces or spaces from the front
     */
    public String readCommand() {
        String fullCommand = scanner.nextLine();
        return fullCommand.trim();
    }
}