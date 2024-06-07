package de.dhbw.cm.application;

import java.io.IOException;

public interface FileBuilder {
    void readLine() throws IOException;
    FileContent getFileContent();
}
