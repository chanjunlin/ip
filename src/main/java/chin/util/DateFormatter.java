package chin.util;

import java.time.format.DateTimeFormatter;

/**
 * Utility class to handle formatting or parsing dates with multiple formats
 */
public class DateFormatter {
    public static final DateTimeFormatter[] DATEFORMAT = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
    };
}
