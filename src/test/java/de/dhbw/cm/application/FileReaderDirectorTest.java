package de.dhbw.cm.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderDirectorTest {
    @Test
    public void testFileReaderDirector() throws IOException {
        FileBuilder fileBuilder = new LineByLineFileBuilder("/Users" +
                "/mikawohlfart/Documents/GitHub/SWE-2/swe2/src/test/java/resources/notes.json");
        FileReaderDirector director = new FileReaderDirector(fileBuilder);
        director.construct();
        assertNotNull(director.getFileContent());
        assertFalse(director.getFileContent().getContent().isEmpty());
    }
}
