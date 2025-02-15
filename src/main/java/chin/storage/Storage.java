package chin.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chin.task.Deadline;
import chin.task.Event;
import chin.task.Task;
import chin.util.ChinChinException;
import chin.util.CustomList;

/**
 * Represents a storage class that handles all the miscellaneous tasks
 */
public class Storage {
    private final String actualFilePath;

    public Storage(String filePath) {
        this.actualFilePath = filePath;
    }

    /**
     * Initialises the task list by checking if the data file exists.
     * If it does, load the tasks from it.
     * Else, create a new file.
     *
     * @return A CustomList containing tasks loaded from the the textfile or an empty CustomList if no file exists.
     */
    public CustomList initialiseTasks() throws ChinChinException {
        File file = new File(actualFilePath);
        if (file.exists()) {
            return loadTasks();
        } else {
            createNewFile();
            return new CustomList(actualFilePath);
        }
    }

    /**
     * Create a new file for storing tasks.
     */
    public void createNewFile() throws ChinChinException {
        try {
            File newFile = new File(actualFilePath);
            File parentDir = newFile.getParentFile();
            boolean isParentExists = parentDir.exists();
            if (!isParentExists) {
                parentDir.mkdirs();
            }

            newFile.createNewFile();
        } catch (IOException e) {
            throw new ChinChinException("Paisei, got something wrong. Your todo list might not be saved.");
        }
    }

    /**
     * Loads tasks from the sepcified data file into a CustomList.
     *
     * @return A CustomList containing all tasks read from the text file
     */
    public CustomList loadTasks() throws ChinChinException {
        CustomList taskList = new CustomList(actualFilePath);
        taskList.setStorage(this);
        try (BufferedReader reader = new BufferedReader(new FileReader(actualFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = checkTask(line);
                taskList.addToList(task);
            }
        } catch (IOException e) {
            throw new ChinChinException("Paisei got error: " + e.getMessage());
        } catch (ChinChinException e) {
            throw new ChinChinException(e.getMessage() + " I don't know why got error");
        }
        return taskList;
    }

    /**
     * Updates the data file with current tasks in the storage
     *
     * @param taskList The ArrayList of Task objects to save to the text file
     */
    public void updateList(List<Task> taskList) throws ChinChinException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(actualFilePath))) {
            for (Task task : taskList) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ChinChinException("Paisei got error: " + e.getMessage());
        }
    }

    /**
     * Retrieves the Task details and return them in a string
     *
     * @param task The task object to convert.
     * @return The string representation of this Task for storing
     */
    public String taskToString(Task task) {
        return task.isDone() + " " + task.getUserInput();
    }

    /**
     * Parses a line from the data file and returns the appropriate Task object based on its task type
     *
     * @param line The input string representing one line on the data file.
     * @return The corresponding Task object based on the parsed information.
     * @throws ChinChinException If there is an error parsing / invalid format is detected.
     */
    public Task checkTask(String line) throws ChinChinException {
        String[] parts = line.split(" ", 3);
        String isDone = parts[0];
        String command = parts[1].toLowerCase();
        String taskDescription = parts[2];
        String userInput = command + " " + taskDescription;

        try {
            switch (command) {
            case ("todo"):
                Task todoTask = CustomList.createTodoTask(userInput);
                if (isDone.equals("true")) {
                    todoTask.mark();
                }
                return todoTask;
            case ("deadline"):
                Deadline deadlineTask = CustomList.createDeadlineTask(userInput);
                if (isDone.equals("true")) {
                    deadlineTask.mark();
                }
                return deadlineTask;
            case ("event"):
                Event eventTask = CustomList.createEventTask(userInput);
                if (isDone.equals("true")) {
                    eventTask.mark();
                }
                return eventTask;
            default:
                throw new ChinChinException("Jialat... got problem");
            }
        } catch (Exception e) {
            throw new ChinChinException(e.getMessage() + " I don't know why got problem sia");
        }
    }
}
