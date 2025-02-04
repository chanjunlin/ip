package ChinChin.util;

import ChinChin.command.*;
import ChinChin.main.*;
import ChinChin.storage.*;
import ChinChin.task.*;
import ChinChin.ui.*;
import ChinChin.util.*;

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

        switch (command) {
        case ("bye"):
            // Fallthrough
        case ("exit"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        case ("mark"):
            return new MarkCommand(userInput);
        case ("unmark"):
            return new UnmarkCommand(userInput);
        case ("delete"):
            return new DeleteCommand(userInput);
        default:
            return new AddCommand(userInput);
        }
    }
}