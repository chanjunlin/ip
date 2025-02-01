import java.util.Scanner;

/**
 * Main application class for managing tasks
 */
public class ChinChin {

    private static CustomList customList;
    private static String LINE_SEPARATOR = "-------------------------------------";
    private static Storage storage = new Storage();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initialiseTasks();
        displayGreeting();

        while (true) {
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("bye") || userInput.equals("goodbye")) { // if user types "bye"
                goodbye();
                break;
            } else {
                processUserInput(userInput);
            }
        }
        scanner.close();
    }

    /**
     * Display bot's greeting message.
     */
    public static void displayGreeting() {
        String greetings = "Hello, I am ChinChin\nWhat can I do for you?";
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
    public static void printInfo(String taskInfo) {
        System.out.println("\n" + LINE_SEPARATOR);
        System.out.println(taskInfo);
        System.out.println("Now you have " + customList.size() + " tasks in the list.");
        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Handling user's input
     *
     * @param input
     */
    public static void processUserInput(String input) {
        try {
            String userInput = input.toLowerCase();

            String[] parts = userInput.split(" ", 2);
            String command = parts[0];

            switch (command) {
            case ("hello"):
                // Fallthrough
            case ("hi"):
                displayGreeting();
                break;
            case ("list"):
                System.out.println("\n" + LINE_SEPARATOR);
                customList.showList();
                System.out.println(LINE_SEPARATOR + "\n");
                break;
            case ("mark"):
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChinChinException("The task number to mark cannot be empty.");
                }
                System.out.println("\n" + LINE_SEPARATOR);
                customList.markTask(Integer.parseInt(parts[1].trim()));
                System.out.println(LINE_SEPARATOR + "\n");
                break;
            case ("unmark"):
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChinChinException("The task number to unmark cannot be empty.");
                }
                System.out.println("\n" + LINE_SEPARATOR);
                customList.unmarkTask(Integer.parseInt(parts[1].trim()));
                System.out.println(LINE_SEPARATOR + "\n");
                break;
            case ("todo"):
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChinChinException("There is no description for the task.");
                }

                String todoInfo = customList.todoTask(userInput);
                printInfo(todoInfo);
                break;
            case ("deadline"):
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChinChinException("There is no description for the deadline task.");
                }

                // Check if the deadline command includes "/by"
                if (!parts[1].contains("/by")) {
                    throw new ChinChinException("Deadlines must include '/by' followed by a due date.");
                }

                String deadlineInfo = customList.deadlineTask(userInput);
                printInfo(deadlineInfo);
                break;
            case ("event"):
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChinChinException("There is no description for the event.");
                }

                // Check if the event command includes both "/from" and "/to"
                if (!parts[1].contains("/from") || !parts[1].contains("/to")) {
                    throw new ChinChinException("Events must include '/from' followed by start time and " +
                            "'/to' followed by end time.");
                }
                String eventInfo = customList.eventTask(userInput);
                printInfo(eventInfo);
                break;
            case ("delete"):
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChinChinException("What can I delete if you don't specify the number?");
                }

                String deleteInfo = customList.deleteTask(Integer.parseInt(parts[1].trim()));
                printInfo(deleteInfo);
                break;
            default:
                System.out.println("\n" + LINE_SEPARATOR);
                System.out.println("What do you mean?");
                System.out.println(LINE_SEPARATOR + "\n");
                break;
            }
        } catch (ChinChinException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            // Handle invalid number inputs for "mark" or "unmark"
            System.out.println("OOPS!!! The task number must be a valid integer.");
            System.out.println(LINE_SEPARATOR + "\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The list is not that long!");
            System.out.println(LINE_SEPARATOR + "\n");
        }
    }

    public static void initialiseTasks() {
        customList = storage.initialiseTasks();
    }
}
