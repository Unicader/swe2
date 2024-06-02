package de.dhbw.cm.logging;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONReadException extends Exception {
    private static final String LOG_FILE = "src/main/java/resources/error.log";

    public JSONReadException(String message) {
        super(message);
        logError(message);
    }

    private void logError(String errorMessage) {
        JSONWriteException.duplicate(errorMessage, LOG_FILE);
    }
}
