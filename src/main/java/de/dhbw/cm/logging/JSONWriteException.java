package de.dhbw.cm.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONWriteException extends Exception {
    private static final String LOG_FILE = "src/main/java/resources/error.log";

    public JSONWriteException(String message) {
        super(message);
        logError(message);
    }

    private void logError(String errorMessage) {
        duplicate(errorMessage, LOG_FILE);
    }

    static void duplicate(String errorMessage, String logFile) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.println(timestamp + " - " + errorMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
