package chin.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chin.command.ChinChinCommand;
import chin.storage.Storage;
import chin.ui.ChinChinUI;

class ChinChinParserTest {
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
    void validExitCommandTest() throws ChinChinException {
        String input = "bye";
        ChinChinCommand command = ChinChinParser.parse(input);
        String result = command.execute(customList, chinChinUI, storage);
        assertEquals("Bye. Hope to see you again soon!", result);
    }

    @Test
    void invalidCommandTest() throws ChinChinException {
        String input = "lalala";
        ChinChinCommand command = ChinChinParser.parse(input);
        assertEquals("Paisei, I don't know what you saying..",
            command.execute(customList, chinChinUI, storage));
    }

    @Test
    void validGreetingCommandTest() throws ChinChinException {
        String input = "hello";
        ChinChinCommand command = ChinChinParser.parse(input);
        String result = command.execute(customList, chinChinUI, storage);
        assertEquals("Nihao, I'm ChinChin\nWhat you want?", result);
    }

}
