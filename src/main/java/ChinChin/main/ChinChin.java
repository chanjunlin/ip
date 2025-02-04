package ChinChin.main;

import java.util.Scanner;

import ChinChin.storage.Storage;
import ChinChin.ui.ChinChinUI;
import ChinChin.util.ChinChinException;
import ChinChin.util.ChinChinParser;
import ChinChin.util.CustomList;
import ChinChin.command.*;
import ChinChin.main.*;
import ChinChin.storage.*;
import ChinChin.task.*;
import ChinChin.ui.*;
import ChinChin.util.*;

/**
 * Main application class for managing tasks
 */
public class ChinChin {

    private static CustomList customList;
    private static Storage storage;
    private static ChinChinUI chinChinUI;

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
        chinChinUI.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = chinChinUI.readCommand();
                ChinChinCommand c = ChinChinParser.parse(fullCommand);
                c.execute(customList, chinChinUI, storage);
                isExit = c.isExit();
            } catch (ChinChinException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Reinitialises the lists of tasks from storage
     */
    public static void initialiseTasks() {
        customList = storage.initialiseTasks();
    }

    /**
     * Entry point of the program
     *
     * @param args Command line arguments passed to the program
     */
    public static void main(String[] args) {
        new ChinChin("data/ChinChinTaskList.txt").run();
    }


}
