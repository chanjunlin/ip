package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents the MarkCommand that will mark the task at the specific index
 */
public class MarkCommand extends ChinChinCommand {

    private final int indexToMark;

    public MarkCommand(String userInput) throws ChinChinException {
        this.indexToMark = extractIndex(userInput);
    }

    /**
     * Executes the Mark command
     *
     * @param taskList   The customList holding all the tasks
     * @param chinChinUI The ChinChinUI that displays all the UI
     * @param storage    The storage that is responsible for saving tasks
     * @throws ChinChinException If there is any errors executing the command
     */
    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        return taskList.markTask(this.indexToMark);
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
     * Retrieves the index of the task to be marked
     *
     * @param userInput The user's input
     * @return The index of the task
     * @throws ChinChinException If there is a missing index or the number was not in integer but a string
     */
    public int extractIndex(String userInput) throws ChinChinException {
        int markIndex = userInput.indexOf("mark");
        int endMarkIndex = markIndex + "mark ".length();

        if (endMarkIndex > userInput.length()) {
            throw new ChinChinException("Task number empty might as well don't mark.");
        }

        String index = userInput.substring(endMarkIndex).trim();

        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new ChinChinException("key in proper number please");
        } catch (IndexOutOfBoundsException e) {
            throw new ChinChinException("er.. check again! The list not that long.");
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
            help mark command
            """;
    }
}
