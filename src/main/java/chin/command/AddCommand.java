package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents a command to create a new task to add to the list
 */
public class AddCommand extends ChinChinCommand {
    private String userInput;

    /**
     * Constructs a AddCommand object
     *
     * @param userInput The user's input
     * @throws ChinChinException If the there is no index provided or if it's not an integer
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the Add command which will create a new task, depending on the input
     *
     * @param taskList   The customList holding all the tasks
     * @param chinChinUI The ChinChinUI that displays all the UI
     * @param storage    The storage that is responsible for saving tasks
     * @throws ChinChinException If there is any errors executing the command
     */
    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        if (userInput.startsWith("todo") || userInput.startsWith("Todo")) {
            String taskInfo = taskList.todoTask(userInput);
            int taskListSize = taskList.size();
            return ChinChinUI.printInfo(taskInfo, taskListSize);
        } else if (userInput.startsWith("deadline") || userInput.startsWith("Deadline")) {
            String taskInfo = taskList.deadlineTask(userInput);
            int taskListSize = taskList.size();
            return ChinChinUI.printInfo(taskInfo, taskListSize);
        } else if (userInput.startsWith("event") || userInput.startsWith("Event")) {
            String taskInfo = taskList.eventTask(userInput);
            int taskListSize = taskList.size();
            return ChinChinUI.printInfo(taskInfo, taskListSize);
        } else {
            throw new ChinChinException("Huh..");
        }
    }

    /**
     * Indicates if this command will make the program close
     *
     * @return False
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
        return "add";
    }

}
