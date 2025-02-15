package chin.main;

import chin.command.ChinChinCommand;
import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.ChinChinParser;
import chin.util.CustomList;

/**
 * Main application class for managing tasks
 */
public class ChinChin {

    private final CustomList customList;
    private final Storage storage;
    private final ChinChinUI chinChinUI;

    private String commandType;

    /**
     * Creates a new ChinChin instance
     *
     * @param filePath The file path to store all the tasks
     */
    public ChinChin(String filePath) throws ChinChinException {
        try {
            chinChinUI = new ChinChinUI();
            storage = new Storage(filePath);
            customList = storage.initialiseTasks();
        } catch (ChinChinException e) {
            throw new ChinChinException("Got error: " + e.getMessage());
        }
    }

    /**
     * Testing
     */
    public String processUserInput(String text) {
        try {
            ChinChinCommand command = ChinChinParser.parse(text);
            commandType = command.getcommandType();
            return command.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the command's type
     *
     * @return String
     */
    public String getCommandType() {
        return this.commandType;
    }
}
