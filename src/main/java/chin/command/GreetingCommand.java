package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * test
 */
public class GreetingCommand extends ChinChinCommand {

    private static int greetingsCount = 0;

    private String userInput;

    public GreetingCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        greetingsCount += 1;
        if (greetingsCount >= 3) {
            return "Stop greeting me liao... just tell me what you need.";
        }
        return ChinChinUI.displayGreeting();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getcommandType() {
        return "greetings";
    }
}
