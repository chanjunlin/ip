package ChinChin.command;

import ChinChin.util.ChinChinException;
import ChinChin.util.CustomList;
import ChinChin.command.*;
import ChinChin.main.*;
import ChinChin.storage.*;
import ChinChin.task.*;
import ChinChin.ui.*;
import ChinChin.util.*;

/**
 * Represents a command to delete a task from the task list
 */
public class DeleteCommand extends ChinChinCommand {

    private int deleteIndex;

    /**
     * Constructs a DeleteCommand object
     *
     * @param userInput The user's input
     * @throws ChinChinException If the there is no index provided or if it's not an integer
     */
    public DeleteCommand(String userInput) throws ChinChinException {
        this.deleteIndex = extractIndex(userInput);
    }

    /**
     * Executes the delete command
     *
     * @param taskList   The customList holding all the tasks
     * @param chinChinUI The ChinChinUI that displays all the UI
     * @param storage    The storage that is responsible for saving tasks
     * @throws ChinChinException If there is any errors executing the command
     */
    @Override
    public void execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        taskList.deleteTask(this.deleteIndex);
    }

    /**
     * Indicates if this command will make the program close
     *
     * @return false as it's not the Exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Retrieves the index of the task to be deleted
     *
     * @param userInput The user's input
     * @return The index of the task
     * @throws ChinChinException If there is a missing index or the number was not in integer but a string
     */
    public int extractIndex(String userInput) throws ChinChinException {
        int indexOfDelete = userInput.indexOf("delete");
        int endMarkIndex = indexOfDelete + "delete ".length();

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
}