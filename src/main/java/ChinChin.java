import java.util.Scanner;

public class ChinChin {

    private static CustomList customList = new CustomList();
    private static String line = "-------------------------------------";

    public static void main(String[] args) {

        // Constants
        Scanner scanner = new Scanner(System.in);

        greeting();

        while (true) {
            String userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            if (userInput.equals("bye") || userInput.equals("goodbye")) { // if user types "bye"
                goodbye();
                break;
            } else {
                processInput(userInput);
            }
        }
    }

    // Bot's greeting
    public static void greeting() {
        String greetings = "Hello, I am ChinChin\nWhat can I do for you?";
        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line + "\n");
    }

    // Bot's goodbye
    public static void goodbye() {
        String goodbyes = "Bye. Hope to see you again soon!";
        System.out.println("\n" + line);
        System.out.println(goodbyes);
        System.out.println(line + "\n");
    }

    // Print the response for each user input
    public static void printInfo(String taskInfo) {
        System.out.println("\n" + line);
        System.out.println(taskInfo);
        System.out.println("Now you have " + customList.size() + " tasks in the list.");
        System.out.println(line + "\n");
    }

    // Hnadling user input
    public static void processInput(String input) {
        try {
            String userInput = input.toLowerCase();

            String[] parts = userInput.split(" ", 2);
            String command = parts[0];

            switch (command) {
                case ("hello"):
                case ("hi"):
                    greeting();
                    break;
                case ("list"):
                    System.out.println("\n" + line);
                    customList.showList();
                    System.out.println(line + "\n");
                    break;
                case ("mark"):
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChinChinException("The task number to mark cannot be empty.");
                    }
                    System.out.println("\n" + line);
                    customList.markTask(Integer.parseInt(parts[1].trim()));
                    System.out.println(line + "\n");
                    break;
                case ("unmark"):
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChinChinException("The task number to unmark cannot be empty.");
                    }
                    System.out.println("\n" + line);
                    customList.unmarkTask(Integer.parseInt(parts[1].trim()));
                    System.out.println(line + "\n");
                    break;
                case ("todo"):
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChinChinException("There is no description for the task.");
                    }

                    String todoInfo = customList.todoTask(parts[1]);
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

                    String deadlineInfo = customList.deadlineTask(parts[1]);
                    printInfo(deadlineInfo);
                    break;
                case ("event"):
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChinChinException("There is no description for the event.");
                    }

                    // Check if the event command includes both "/from" and "/to"
                    if (!parts[1].contains("/from") || !parts[1].contains("/to")) {
                        throw new ChinChinException("Events must include '/from' followed by start time and '/to' followed by end time.");
                    }
                    String eventInfo = customList.eventTask(parts[1]);
                    printInfo(eventInfo);
                    break;
                default:
                    System.out.println("\n" + line);
                    System.out.println("What do you mean?");
                    System.out.println(line + "\n");
                    break;
            }
        } catch (ChinChinException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            // Handle invalid number inputs for "mark" or "unmark"
            System.out.println("OOPS!!! The task number must be a valid integer.");
            System.out.println(line + "\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The list is not that long!");
            System.out.println(line + "\n");
        }
    }
}
