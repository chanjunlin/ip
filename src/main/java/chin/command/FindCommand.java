package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents a command to find tasks based on the keywords
 */
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

    /**
     * Extracts the keyword from the user's input
     *
     * @param userInput The user's input
     * @return The keyword
     * @throws ChinChinException If there is no keyword to search for
     */
    public String extractKeyword(String userInput) throws ChinChinException {
        int findIndex = userInput.indexOf("find");
        int endFindIndex = findIndex + "find ".length();

        if (endFindIndex > userInput.length()) {
            throw new ChinChinException("What can i find if the keyword is empty...");
        }

        return userInput.substring(endFindIndex).trim();
    }
}
