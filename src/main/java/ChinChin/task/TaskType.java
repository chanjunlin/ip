package ChinChin.task;

import ChinChin.command.*;
import ChinChin.main.*;
import ChinChin.storage.*;
import ChinChin.task.*;
import ChinChin.ui.*;
import ChinChin.util.*;

/**
 * Enumeration representing the different types of tasks.
 */
public enum TaskType {
    TODO("[T]"),        // Represents TODO task
    DEADLINE("[D]"),    // Represents DEADLINE task
    EVENT("[E]");       // Represents EVENT task

    private final String tag;

    /**
     * Constructs a TaskType with the specified tag
     *
     * @param tag
     */
    TaskType(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the tag of this task type.
     *
     * @return The tag associated with this TaskType.
     */
    public String getTag() {
        return this.tag;
    }
}