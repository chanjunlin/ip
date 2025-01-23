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
                System.out.println("\n" + line);
                customList.markTask(Integer.parseInt(parts[1].trim()));
                System.out.println(line + "\n");
                break;
            case ("unmark"):
                System.out.println("\n" + line);
                customList.unmarkTask(Integer.parseInt(parts[1].trim()));
                System.out.println(line + "\n");
                break;
            case ("todo"):
                String todoInfo = customList.todoTask(parts[1]);
                printInfo(todoInfo);
                break;
            case ("deadline"):
                String deadlineInfo = customList.deadlineTask(parts[1]);
                printInfo(deadlineInfo);
                break;
            case ("event"):
                String eventInfo = customList.eventTask(parts[1]);
                printInfo(eventInfo);
                break;
            default:
                System.out.println("\n" + line);
                System.out.println("Please specify what to do :(");
                System.out.println(line + "\n");
                break;
        }
    }
}
