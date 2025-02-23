package chin.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chin.storage.Storage;
import chin.ui.ChinChinUI;
import chin.util.ChinChinException;
import chin.util.CustomList;

class AddCommandTest {

    private static final String TEST_DATA_FILE = "data/ChinChinTaskList.txt";
    private ChinChinUI chinChinUI;
    private Storage storage;
    private CustomList customList;

    @BeforeEach
    public void setUp() throws IOException, ChinChinException {
        resetDataFile();
        chinChinUI = new ChinChinUI();
        storage = new Storage(TEST_DATA_FILE);
        customList = storage.initialiseTasks();
    }

    private void resetDataFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_DATA_FILE))) {
            writer.write("");
        }
    }

    @Test
    void validTodoInputTest() throws ChinChinException {
        AddCommand addCommand = new AddCommand("todo Read book");
        String result = addCommand.execute(customList, chinChinUI, storage);
        assertEquals("Oki, task added liao ✅:\n" + "[T] [ ] Read book\n" + "Now you got 1 tasks in the list.",
            result);
    }

    @Test
    void invalidTodoInputTest() throws ChinChinException {
        try {
            AddCommand addCommand = new AddCommand("todo ");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("your task description is empty bro..", e.getMessage());
        }
    }

    @Test
    void validDeadlineTest() throws ChinChinException {
        AddCommand addCommand = new AddCommand("deadline Read a book /by 12-12-2025");
        String result = addCommand.execute(customList, chinChinUI, storage);
        assertEquals("Oki, task added liao ✅:\n" + "[D] [ ] Read a book\n"
                + "DEADLINE: Dec 12 2025 12:00 am\n" + "Now you got 1 tasks in the list.", result);
    }

    @Test
    void emptyDeadlineDescriptionTest() {
        try {
            AddCommand addCommand = new AddCommand("deadline ");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("why is your task description empty?", e.getMessage());
        }
    }

    @Test
    void emptyDeadlineDueDateTest() {
        try {
            AddCommand addCommand = new AddCommand("deadline read a book");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("you never put deadline then use the deadline feature for what??", e.getMessage());
        }
    }

    @Test
    void validEventTest() throws ChinChinException {
        AddCommand addCommand = new AddCommand("event Read a book /from 12-12-2025 /to 13-12-2025");
        String result = addCommand.execute(customList, chinChinUI, storage);
        assertEquals("Oki, task added liao ✅:\n" + "[E] [ ] Read a book "
            + "(FROM: Dec 12 2025 12:00 am TO: Dec 13 2025 12:00 am)\n"
            + "Now you got 1 tasks in the list.", result);
    }

    @Test
    void emptyEventTaskDescriptionTest() {
        try {
            AddCommand addCommand = new AddCommand("event ");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("bro your event task got no description", e.getMessage());
        }
    }

    @Test
    void emptyEventTaskStartingDateTest() {
        try {
            AddCommand addCommand = new AddCommand("event read a book");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("if you don't state the starting, then just use 'deadline' feature", e.getMessage());
        }
    }

    @Test
    void emptyEventTaskEndingDateTest() {
        try {
            AddCommand addCommand = new AddCommand("event read a book /from 12-12-2025");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("no ending then isn't it the same as a normal task..", e.getMessage());
        }
    }

    @Test
    void wrongEventTaskStartingDateTest() {
        try {
            AddCommand addCommand = new AddCommand("event read a book /from 12/12-2025 /to 13/12/2025");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("Can you please choose proper date format?", e.getMessage());
        }
    }

    @Test
    void wrongEventTaskEndingDateTest() {
        try {
            AddCommand addCommand = new AddCommand("event read a book /from 12-12-2025 /to 13-12/2025");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("Can you please choose proper date format?", e.getMessage());
        }
    }

    @Test
    void missingEventDatesTest() {
        try {
            AddCommand addCommand = new AddCommand("event read a book /from /to");
            String result = addCommand.execute(customList, chinChinUI, storage);
        } catch (ChinChinException e) {
            assertEquals("jialat.. Please remember to key in your dates after '/from' and '/to' hor, "
                + "thanks", e.getMessage());
        }
    }
}


