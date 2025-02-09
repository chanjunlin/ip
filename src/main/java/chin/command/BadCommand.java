package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * testing
 */
public class BadCommand extends ChinChinCommand {

    private String userInput;

    public BadCommand(String userInput) {
        this.userInput = userInput;
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
