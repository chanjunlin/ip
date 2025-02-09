package chin.ui;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the ChinChin list
 */
public class ChinChinUI {
    private static final String LINE_SEPARATOR = "-------------------------------------";
    private final Scanner scanner;

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
     *
     * @return
     */
    public static String displayGreeting() {
        return "Nihao, I'm ChinChin\nWhat you want?";
    }

    /**
     * Display bot's farewell message.
     *
     * @return
     */
    public static String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print information about the task.
     *
     * @param taskInfo The information about the task being displayed.
     */
    public static String printInfo(String taskInfo, int size) {
        return taskInfo + "\nNow you got " + size + " tasks in the list.";
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
