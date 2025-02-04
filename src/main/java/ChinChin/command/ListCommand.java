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
 * Represent the ListCommand that displays all the tasks in the list
 */
public class ListCommand extends ChinChinCommand {
    public ListCommand() {

    }

    /**
     * Executes the list command
     *
     * @param taskList The customList holding all the tasks
     * @param chinChinUI The ChinChinUI that displays all the UI
     * @param storage The storage that is responsible for saving tasks
     * @throws ChinChinException If there is any errors executing the command
     */
    @Override
    public void execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        taskList.showList();
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
}