package chin.util;

import java.time.format.DateTimeFormatter;

/**
 * Utility class to handle formatting or parsing dates with multiple formats
 */
public class DateFormatter {
    public static final DateTimeFormatter[] DATEFORMAT = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("M-d-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy.dd.MM"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH"),
            DateTimeFormatter.ofPattern("d-M-yyyy HH"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy"),
            DateTimeFormatter.ofPattern("d MMMM yyyy"),
            DateTimeFormatter.ofPattern("MMMM d, yyyy"),
            DateTimeFormatter.ofPattern("EEE, MMM d, yyyy"),
            DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a"),
            DateTimeFormatter.ofPattern("d/M/yyyy hh:mm:ss a"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
            DateTimeFormatter.ofPattern("MMMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM"),
    };
}
