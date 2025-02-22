package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents a command to unmark a task at the specific index
 */
public class UnmarkCommand extends ChinChinCommand {

    private final int indexToUnmark;

    public UnmarkCommand(String userInput) throws ChinChinException {
        this.indexToUnmark = extractIndex(userInput);
    }

    /**
     * Executes the command
     *
     * @param taskList   The customList holding all the tasks
     * @param chinChinUI The ChinChinUI that displays all the UI
     * @param storage    The storage that is responsible for saving tasks
     * @throws ChinChinException If there is any errors executing the command
     */
    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        return taskList.unmarkTask(this.indexToUnmark);
    }

    /**
     * Indicates if this command will make the program close
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Retrieves the index of the task to be unmarked
     *
     * @param userInput The user's input
     * @return The index of the task
     * @throws ChinChinException If there is a missing index or the number was not in integer but a string
     */
    public int extractIndex(String userInput) throws ChinChinException {
        int indexOfUnmark = userInput.indexOf("unmark");
        int endMarkIndex = indexOfUnmark + "unmark ".length();

        if (endMarkIndex > userInput.length()) {
            throw new ChinChinException("Task number empty might as well don't unmark.");
        }

        String index = userInput.substring(endMarkIndex).trim();

        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new ChinChinException("key in proper number please");
        }
    }

    /**
     * Command Type
     *
     * @return The commandType
     */
    @Override
    public String getcommandType() {
        return "mark";
    }

    @Override
    public String displayHelpInfo() {
        return """
            help unmark command;
            """;
    }
}
