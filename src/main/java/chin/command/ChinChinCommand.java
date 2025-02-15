package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Abstract base class for all ChinChin commands
 */
public abstract class ChinChinCommand {

    /**
     * Executes the different commands
     *
     * @param taskList      The customList holding all the tasks
     * @param chinChinUI    The ChinChinUI that displays all the UI
     * @param storage       The storage that is responsible for saving tasks
     * @return              The reply of the program
     * @throws ChinChinException    If there is any errors executing the command
     */
    public abstract String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage)
            throws ChinChinException;

    /**
     * Indicates if this command will make the program close
     *
     * @return True if exit command, false if not exit command
     */
    public abstract boolean isExit();

    public abstract String getcommandType();
}
