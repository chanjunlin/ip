package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents a command to return a confused string when the user keys in an unknown command.
 */
public class BadCommand extends ChinChinCommand {

    public BadCommand(String userInput) {
    }

    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        return "Paisei, I don't know what you saying..";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getcommandType() {
        return "chinChinException";
    }
}
