package ChinChin.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ChinChin.command.*;
import ChinChin.main.*;
import ChinChin.storage.*;
import ChinChin.task.*;
import ChinChin.ui.*;
import ChinChin.util.*;

/**
 * Represents a storage class that handles all the miscellaneous tasks
 */
public class Storage {
    private final String FILEPATH;

    public Storage(String filePath) {
        this.FILEPATH = filePath;
    }

    /**
     * Initialises the task list by checking if the data file exists.
     * If it does, load the tasks from it.
     * Else, create a new file.
     *
     * @return A CustomList containing tasks loaded from the the textfile or an empty CustomList if no file exists.
     */
    public CustomList initialiseTasks() {
        File file = new File(FILEPATH);
        if (file.exists()) {
            return loadTasks();
        } else {
            createNewFile();
            return new CustomList(FILEPATH);
        }
    }

    /**
     * Create a new file for storing tasks.
     */
    public void createNewFile() {
        try {
            File newFile = new File(FILEPATH);

            File parentDir = newFile.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            boolean created = newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Paisei, got something wrong. Your todo list might not be saved.");
        }
    }

    /**
     * Loads tasks from the sepcified data file into a CustomList.
     *
     * @return A CustomList containing all tasks read from the text file
     */
    public CustomList loadTasks() {
        CustomList taskList = new CustomList(FILEPATH);
        taskList.setStorage(this);
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = checkTask(line);
                taskList.addToList(task);
            }
        } catch (IOException e) {
            System.out.println("Paisei got error: " + e.getMessage());
        } catch (ChinChinException e) {
            System.out.println(e.getMessage() + " I don't know why got error");
        }
        return taskList;
    }

    /**
     * Updates the data file with current tasks in the storage
     *
     * @param taskList The ArrayList of Task objects to save to the text file
     */
    public void updateList(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH))) {
            for (Task task : taskList) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Paisei got error: " + e.getMessage());
        }
    }

    /**
     * Retrieves the Task details and return them in a string
     *
     * @param task The task object to convert.
     * @return The string representation of this Task for storing
     */
    public String taskToString(Task task) {
        String lineToWrite = task.isDone() + " " + task.getUserInput();
        return lineToWrite;
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