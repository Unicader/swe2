package de.dhbw.cm.application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileContentTest {

    @Test
    public void testSetAndGetContent() {
        FileContent fileContent = new FileContent();
        String content = "Test content";
        fileContent.setContent(content);
        assertEquals(content, fileContent.getContent());
    }
}
