package chin.command;

import java.util.ArrayList;
import java.util.Arrays;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * test
 */
public class HelpCommand extends ChinChinCommand {
    private static final ArrayList<String> VALID_COMMANDS = new ArrayList<>(
        Arrays.asList("hi", "hello", "greetings", "bye", "goodbye", "delete", "find", "help", "list", "mark", "unmark",
            "summary", "todo", "deadline", "event")
    );
    private String helpRequest;

    public HelpCommand(String userInput) throws ChinChinException {
        this.helpRequest = parseUserInput(userInput);
    }

    /**
     * test
     *
     * @param userInput test
     * @return test
     * @throws ChinChinException test
     */
    public String parseUserInput(String userInput) throws ChinChinException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 1) {
            return null;
        }
        if (!VALID_COMMANDS.contains(parts[1])) {
            throw new ChinChinException("type in the command properly bro..");
        }
        return parts[1];
    }

    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        if (helpRequest == null) {
            return """
                 hi, hello, greetings   -> Get a greeting,
                 🗑️ delete              -> Delete an item
                 🔍 find                -> Find an item
                 ✅ mark                -> Mark an item
                 ❌ unmark              -> Unmark an item
                 📅 todo, deadline, event -> Add a new event, to-do, or deadline
                """;
        } else {
            return getSpecificHelp();
        }
    }

    public String getSpecificHelp() {
        return switch (this.helpRequest) {
        case "hi", "hello", "greetings" -> "You'll just get a greeting from me";
        case "bye", "goodbye" -> "I say bye bye to you";
        case "delete" -> "Help you delete your tasks";
        case "find" -> "Help you find any task with the keyword";
        case "list" -> "List out your entire list lor";
        case "mark" -> "Help you mark your tasks as done";
        case "unmark" -> "Help you unmark your tasks as undone";
        case "todo" -> "How to use 'TODO'? Just type:\ntodo [your task description]";
        case "deadline" -> "How to use 'DEADLINE', Just type:\ntodo [your task description] [/by] [date]";
        case "event" -> "How to use 'EVENT', Just type:\ntodo [your task description] [/from] [date] [/by] [date]";
        case "summary" -> "Helpfully show you the summary of your tasks ";
        default -> "Er no idea boss";
        };
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getcommandType() {
        return "help";
    }

    @Override
    public String displayHelpInfo() {
        return null;
    }
}
