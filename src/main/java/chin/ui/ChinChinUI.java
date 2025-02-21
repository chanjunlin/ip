package chin.ui;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the ChinChin list
 */
public class ChinChinUI {
    private final Scanner scanner;

    /**
     * Constructs a new ChinChin instance
     */
    public ChinChinUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display bot's greeting message.
     *
     * @return The greeting string
     */
    public static String displayGreeting() {
        return "Nihao, I'm ChinChin\nWhat you want?";
    }

    /**
     * Display bot's farewell message.
     *
     * @return The farewell string
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
}
