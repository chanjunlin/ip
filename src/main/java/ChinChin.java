import java.util.Scanner;

public class ChinChin {
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

            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("\n" + line);
                System.out.println(goodbyes);
                System.out.println(line + "\n");
                break;
            } else {
                System.out.println("\n" + line);
                System.out.println(userInput);
                System.out.println(line + "\n");
            }
        }
    }
}
