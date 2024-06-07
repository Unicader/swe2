package de.dhbw.cm.application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileBuilderTest {

    @Test
    public void testFileBuilderInterface() {
        FileBuilder fileBuilder = new LineByLineFileBuilder("test.txt");
        assertNotNull(fileBuilder);
    }
}
