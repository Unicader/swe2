package de.dhbw.cm.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleReaderTest {

    private final InputStream originalIn = System.in;

    @Test
    public void testReadLine() {
        String simulatedInput = "Hello, World!\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(byteArrayInputStream);

        ConsoleReader consoleReader = new ConsoleReader();
        String result = consoleReader.readLine();

        assertEquals("Hello, World!", result);
    }

    @Test
    public void testReadLineMultipleLines() {
        String simulatedInput = "First line\nSecond line\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(byteArrayInputStream);

        ConsoleReader consoleReader = new ConsoleReader();
        String firstResult = consoleReader.readLine();
        String secondResult = consoleReader.readLine();

        assertEquals("First line", firstResult);
        assertEquals("Second line", secondResult);
    }

    @Test
    public void testReadLineEmptyInput() {
        String simulatedInput = "\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(byteArrayInputStream);

        ConsoleReader consoleReader = new ConsoleReader();
        String result = consoleReader.readLine();

        assertEquals("", result);
    }
}
