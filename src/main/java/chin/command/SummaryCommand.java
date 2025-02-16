package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * test
 */
public class SummaryCommand extends ChinChinCommand {

    public SummaryCommand() {
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
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        return taskList.getSummary();
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
     * Command Type
     *
     * @return The commandType
     */
    @Override
    public String getcommandType() {
        return "summary";
    }
}
