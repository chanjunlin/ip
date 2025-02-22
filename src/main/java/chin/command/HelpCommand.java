package chin.command;

import java.util.ArrayList;
import java.util.Arrays;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents a command that shows the user the help commands
 */
public class HelpCommand extends ChinChinCommand {
    private static final ArrayList<String> VALID_COMMANDS = new ArrayList<>(
        Arrays.asList("hi", "hello", "greetings", "bye", "goodbye", "delete", "find", "help", "list", "mark", "unmark",
            "summary", "todo", "deadline", "event", "date", "view")
    );
    private String helpRequest;

    public HelpCommand(String userInput) throws ChinChinException {
        this.helpRequest = parseUserInput(userInput);
    }

    /**
     * Parses the user input to extract and validate the command.
     *
     * @param userInput The raw input string entered by the user.
     * @return The validated command extracted from the user input, or null if no additional arguments are provided.
     * @throws ChinChinException If the extracted command is not in the list of valid commands,
     *      or if the input format is invalid.
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
                 [Here are the available commands]\n
                 Use 'help [command]' to learn how to use them\n
                 👋 hi, hello, greetings     -> Pretty self-explanatory
                 👋 bye, goodbye             -> Pretty self-explanatory
                 🔍 find                     -> Find an item
                 📑 list                     -> List out your entire list lor
                 ✅ mark                     -> Mark an item
                 ❌ unmark                   -> Unmark an item
                 📅 todo, deadline, event    -> Add a new event, to-do, or deadline
                 📋 view                     -> View the schedule for a specific date
                 🗓 date                     -> Show you the correct date format
                 ✍️ summary                  -> Helpfully show you the summary of your tasks
                 🗑 delete                   -> Erm.. delete your task
                """;
        } else {
            return getSpecificHelp();
        }
    }

    /**
     * Provides specific help information based on the type of user help request.
     *
     * @return A string containing detailed instructions or descriptions for the specified command
     *      or a generic message if the command is unrecognized.
     */
    public String getSpecificHelp() {
        return switch (this.helpRequest) {
        case "hi", "hello", "greetings" -> "You'll just get a greeting from me";
        case "bye", "goodbye" -> "I say bye bye to you";
        case "find" -> "How to use 'FIND'? Just type:\nfind [keyword]";
        case "list" -> "List out your entire list lor";
        case "mark" -> "How to use 'MARK'? Just type:\nmark [index]";
        case "unmark" -> "How to use 'UNAMRK'? Just type:\nunamrk [index]";
        case "todo" -> "How to use 'TODO'? Just type:\ntodo [your task description]";
        case "deadline" -> "How to use 'DEADLINE'? Just type:\ndeadline [your task description] /by [date]";
        case "event" -> "How to use 'EVENT'? Just type:\nevent [your task description] /from [date] /by [date]";
        case "view" -> "How to use 'VIEW'? Just type:\nview [/on] [date]";
        case "date" -> "Just in case I never specify, use [date] as 'dd-mm-yyyy HHmm' or dd/mm/yyyy HHmm'"
            + "\nHHmm can also be omitted, the default timing would be 12am";
        case "summary" -> "Just type:\nsummary";
        case "delete" -> "How to use 'DELETE'? Just type:\n delete [index]";
        default -> "Use [help] to see all the commands please...";
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
