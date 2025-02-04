package ChinChin.command;

import ChinChin.storage.Storage;
import ChinChin.ui.ChinChinUI;
import ChinChin.util.ChinChinException;
import ChinChin.util.CustomList;

public class FindCommand extends ChinChinCommand {

    private String findString;

    public FindCommand(String userInput) throws ChinChinException {
        this.findString = extractKeyword(userInput);
    }

    @Override
    public void execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        taskList.findKeyword(this.findString);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String extractKeyword(String userInput) throws ChinChinException {
        int findIndex = userInput.indexOf("find");
        int endFindIndex = findIndex + "find ".length();

        if (endFindIndex > userInput.length()) {
            throw new ChinChinException("What can i find if the keyword is empty...");
        }

        String keyword = userInput.substring(endFindIndex).trim();
        return keyword;
    }
}
