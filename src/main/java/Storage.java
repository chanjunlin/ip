import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a storage class that handles all the miscellaneous tasks
 */
public class Storage {
    private final String FILEPATH = "./data/ChinChinList.txt";

    public Storage() {

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
            return new CustomList();
        }
    }

    /**
     * Create a new file for storing tasks.
     */
    public void createNewFile() {
        try {
            File newFile = new File(FILEPATH);
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
        CustomList taskList = new CustomList();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = checkTask(line);
                taskList.addToList(task);
            }
        } catch (IOException e) {
            System.out.println("Something wrong while loading tasks");
        } catch (ChinChinException e) {
            System.out.println(e.getMessage());
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
            System.out.println("Something wrong while saving tasks");
        }
    }

    /**
     * Converts a Task object into its string representation during the saving process.
     *
     * @param task The task object to convert.
     * @return The string representation of this Task for storing
     */
    public String taskToString(Task task) {
        return task.show();
    }

    /**
     * Parses a line from the data file and returns the appropriate Task object based on its task type
     *
     * @param line The input string representing one line on the data file.
     * @return The corresponding Task object based on the parsed information.
     * @throws ChinChinException If there is an error parsing / invalid format is detected.
     */
    public Task checkTask(String line) throws ChinChinException {
        char taskType = line.charAt(1);
        boolean isDone = line.charAt(4) == 'X';
        if (isDone) {
            String[] parts = line.split(" ", 3);
        } else {
            String[] parts = line.split(" ", 2);
        }

        try {
            switch (taskType) {
            case 'T':
                String descriptionT = line.substring(line.indexOf("] ") + 2).trim();
                Task todoTask = new Task(descriptionT, TaskType.TODO);
                if (isDone) {
                    todoTask.mark();
                }
                return todoTask;
            case 'D':
                int descEndIndexD = line.indexOf("(by:");
                String descriptionD = line.substring(line.indexOf("] ") + 2, descEndIndexD).trim();
                String dueDateD = line.substring(descEndIndexD + 4, line.lastIndexOf(")")).trim();
                dueDateD = "/by " + dueDateD;
                Task deadlineTask = new Deadline(descriptionD, TaskType.DEADLINE, dueDateD);
                if (isDone) {
                    deadlineTask.mark();
                }
                return deadlineTask;
            case 'E':
                int descEndIndexE = line.indexOf("(from:");
                String descriptionE = line.substring(line.indexOf("] ") + 2, descEndIndexE).trim();
                String timeRange = line.substring(descEndIndexE + 6, line.lastIndexOf(")")).trim();

                String[] times = timeRange.split("to:");
                if (times.length != 2) {
                    throw new ChinChinException("Invalid event time range format!");
                }
                String startTime = times[0].trim();
                String endTime = times[1].trim();

                Task eventTask = new Event(descriptionE, TaskType.TODO, startTime, endTime);
                if (isDone) {
                    eventTask.mark();
                }
                return eventTask;
            default:
                throw new ChinChinException("Jialat... got problem");
            }
        } catch (Exception e) {
            throw new ChinChinException(e.getMessage());
        }
    }
}