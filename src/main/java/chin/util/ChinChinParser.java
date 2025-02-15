package chin.util;


import chin.command.AddCommand;
import chin.command.BadCommand;
import chin.command.ChinChinCommand;
import chin.command.DeleteCommand;
import chin.command.ExitCommand;
import chin.command.FindCommand;
import chin.command.GreetingCommand;
import chin.command.ListCommand;
import chin.command.MarkCommand;
import chin.command.UnmarkCommand;

/**
 * Parses the user input and maps it to a specific ChinChinCommand
 */
public class ChinChinParser {

    /**
     * Parses the user's input string into a corresponding ChinChinCommand
     *
     * @param userInput The raw input string entered by the user
     * @return A specific implementation of ChinChinCommand that corresponds to the parsed command keyword
     * @throws ChinChinException If there are errors parsing or validating the user's input
     */
    public static ChinChinCommand parse(String userInput) throws ChinChinException {
        userInput = userInput.trim();
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        command = command.toLowerCase();

        return switch (command) {
        case ("bye"), ("exit") -> new ExitCommand();
        case ("hi"), ("hello"), ("greetings") -> new GreetingCommand();
        case ("delete") -> new DeleteCommand(userInput);
        case ("find") -> new FindCommand(userInput);
        case ("list") -> new ListCommand();
        case ("mark") -> new MarkCommand(userInput);
        case ("unmark") -> new UnmarkCommand(userInput);
        case ("todo"), ("deadline"), ("event") -> new AddCommand(userInput);
        default -> new BadCommand(userInput);
        };
    }
}
