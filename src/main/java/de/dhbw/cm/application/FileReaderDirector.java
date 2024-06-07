package de.dhbw.cm.application;

import java.io.IOException;

public class FileReaderDirector {
    private FileBuilder fileBuilder;

    public FileReaderDirector(FileBuilder fileBuilder) {
        this.fileBuilder = fileBuilder;
    }

    public void construct() throws IOException {
        fileBuilder.readLine();
    }

    public FileContent getFileContent() {
        return fileBuilder.getFileContent();
    }
}
