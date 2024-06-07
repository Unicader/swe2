package de.dhbw.cm.application;

import de.dhbw.cm.presentation.AnsiCodes;
import de.dhbw.cm.presentation.ConsoleWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleWriterTest {

    private ConsoleWriter consoleWriter;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        consoleWriter = new ConsoleWriter();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void write() {
        consoleWriter.write("Test message");
        assertEquals("Test message", outputStreamCaptor.toString().trim());
    }

    @Test
    void writeWithColor() {
        consoleWriter.write(AnsiCodes.RED, "Test message with color");
        assertEquals("[31mTest message with color\u001B[0m", outputStreamCaptor.toString().trim());
    }

    @Test
    void writeInLine() {
        consoleWriter.writeInLine("Test message in line");
        assertEquals("Test message in line", outputStreamCaptor.toString());
    }

    @Test
    void writeInLineWithColor() {
        consoleWriter.writeInLine(AnsiCodes.GREEN, "Test message in line with color");
        assertEquals("\u001B[32mTest message in line with color\u001B[0m", outputStreamCaptor.toString());
    }

}
