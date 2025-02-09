package chin.main;

import chin.command.ChinChinCommand;
import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.ChinChinParser;
import chin.util.CustomList;
import javafx.application.Platform;


/**
 * Main application class for managing tasks
 */
public class ChinChin {

    private static CustomList customList;
    private static Storage storage;
    private static ChinChinUI chinChinUI;

    private String commandType;

    /**
     * Creates a new ChinChin instance
     *
     * @param filePath The file path to store all the tasks
     */
    public ChinChin(String filePath) {
        chinChinUI = new ChinChinUI();
        storage = new Storage(filePath);
        customList = storage.initialiseTasks();
    }

    /**
     *
     */
    public void run() {
        ChinChinUI.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = chinChinUI.readCommand();
                ChinChinCommand c = ChinChinParser.parse(fullCommand);
                c.execute(customList, chinChinUI, storage);
                isExit = c.isExit();
                if (isExit) {
                    Platform.runLater(() -> {
                        Platform.exit();
                        System.exit(0);
                    });
                }
            } catch (ChinChinException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Entry point of the program
     *
     * @param args Command line arguments passed to the program
     */
    public static void main(String[] args) {
        new ChinChin("data/ChinChinTaskList.txt").run();
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
