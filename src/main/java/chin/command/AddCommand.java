package chin.command;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents a command to create a new task to add to the list
 */
public class AddCommand extends ChinChinCommand {
    private final String userCommand;


    /**
     * Constructs a AddCommand object
     *
     * @param userInput The user's input
     */
    public AddCommand(String userInput) {
        this.userCommand = userInput;
    }

    /**
     * Executes the Add command which will create a new task, depending on the input
     *
     * @param taskList   The customList holding all the tasks
     * @param chinChinUI The ChinChinUI that displays all the UI
     * @param storage    The storage that is responsible for saving tasks
     * @return The reply of the program
     * @throws ChinChinException If there is any errors executing the command
     */
    @Override
    public String execute(CustomList taskList, ChinChinUI chinChinUI, Storage storage) throws ChinChinException {
        if (userCommand.startsWith("todo") || userCommand.startsWith("Todo")) {
            String taskInfo = taskList.todoTask(userCommand);
            int taskListSize = taskList.size();
            return ChinChinUI.printInfo(taskInfo, taskListSize);
        } else if (userCommand.startsWith("deadline") || userCommand.startsWith("Deadline")) {
            String taskInfo = taskList.deadlineTask(userCommand);
            int taskListSize = taskList.size();
            return ChinChinUI.printInfo(taskInfo, taskListSize);
        } else if (userCommand.startsWith("event") || userCommand.startsWith("Event")) {
            String taskInfo = taskList.eventTask(userCommand);
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

    @Override
    public String displayHelpInfo() {
        return """
            add command help
            """;
    }

}
