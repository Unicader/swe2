package de.dhbw.cm.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineByLineFileBuilder implements FileBuilder {
    private String filePath;
    private FileContent fileContent;

    public LineByLineFileBuilder(String filePath) {
        this.filePath = filePath;
        this.fileContent = new FileContent();
    }

    @Override
    public void readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        fileContent.setContent(sb.toString());
    }

    @Override
    public FileContent getFileContent() {
        return fileContent;
    }
}
