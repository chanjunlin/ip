import java.util.Scanner;

public class ChinChin {

    private static CustomList customList = new CustomList();

    public static void main(String[] args) {

        // Constants
        Scanner scanner = new Scanner(System.in);

        String line = "-----------------------------";
        String greetings = "Hello, I am ChinChin\nWhat can I do for you?";
        String goodbyes = "Bye. Hope to see you again soon!";

        // Friendly greetings
        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line + "\n");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.toLowerCase().equals("bye")) { // if user types "bye"
                System.out.println("\n" + line);
                System.out.println(goodbyes);
                System.out.println(line + "\n");
                break;
            } else if (userInput.toLowerCase().equals("list")) { // if user types "list"
                System.out.println("\n" + line);
                customList.showList();
                System.out.println(line + "\n");
            } else { // if user types anything other than "bye" or "list", adds it to the list
                System.out.println("\n" + line);
                System.out.println("added: " + userInput);
                System.out.println(line + "\n");
                customList.addToList(userInput);
            }
        }
    }
}
