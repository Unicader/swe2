package de.dhbw.cm.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class LineByLineFileBuilderTest {


    @Test
    public void testReadLine() throws IOException {
        LineByLineFileBuilder fileBuilder = new LineByLineFileBuilder("/Users" +
                "/mikawohlfart/Documents/GitHub/SWE-2/swe2/src/test/java/resources/notes.json");
        fileBuilder.readLine();
        assertNotNull(fileBuilder.getFileContent());
        assertFalse(fileBuilder.getFileContent().getContent().isEmpty());
    }
}
