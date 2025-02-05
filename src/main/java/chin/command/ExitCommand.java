package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents the ExitCommand to end the program
 */
public class ExitCommand extends ChinChinCommand {

    public ExitCommand() {

    }

    /**
     * Executes the exit command
     *
     * @param taskList   The customList holding all the tasks
     * @param chinChinUI The ChinChinUI that displays all the UI
     * @param storage    The storage that is responsible for saving tasks
     * @throws ChinChinException If there is any errors executing the command
     */
    @Override
    public void execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        ChinChinUI.goodbye();
    }

    /**
     * Indicates if this command will make the program close
     *
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
