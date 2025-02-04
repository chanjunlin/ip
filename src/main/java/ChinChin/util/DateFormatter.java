package ChinChin.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ChinChin.command.*;
import ChinChin.main.*;
import ChinChin.storage.*;
import ChinChin.task.*;
import ChinChin.ui.*;
import ChinChin.util.*;

/**
 * Utility class to handle formatting or parsing dates with multiple formats
 */
public class DateFormatter {
    public static final DateTimeFormatter[] DATEFORMAT = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH"),
            DateTimeFormatter.ofPattern("M/d/yyyy HH"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HH"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
    };
}