package de.dhbw.cm.infrastructure.repository;

import com.google.gson.Gson;
import de.dhbw.cm.domain.Note;
import de.dhbw.cm.logging.JSONReadException;
import de.dhbw.cm.logging.JSONWriteException;
import de.dhbw.cm.presentation.ConsoleReader;
import de.dhbw.cm.presentation.ConsoleWriter;
import de.dhbw.cm.presentation.NoteView;
import de.dhbw.cm.presentation.OverviewView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NoteManagerApp {
    String filepath = "src/main/java/resources/";
    List<Note> notesTookByUser = new ArrayList<>();
    NoteView noteView;
    String userFilePath;
    OverviewView overviewView;

    public NoteManagerApp(String username, ConsoleReader consoleReader, ConsoleWriter consoleWriter, OverviewView overviewView) {
        this.overviewView = overviewView;
        try {
            userFilePath = filepath + username + "/note.json";
            filepath = filepath + username;
            notesTookByUser = returnStoredNotes(userFilePath);
            noteView = new NoteView(this, notesTookByUser, consoleWriter,
                    consoleReader, userFilePath);
        } catch (JSONReadException e) {
            throw new RuntimeException(e);
        }
    }

    public NoteManagerApp(String filepath) throws JSONReadException {
        noteView = new NoteView(this, returnStoredNotes(filepath),
                new ConsoleWriter(), new ConsoleReader(), filepath);
    }

    public List<Note> returnStoredNotes(String filepath) throws JSONReadException {
        Gson gson = new Gson();
        Note[] notesArray = new Note[]{};
        if (Files.exists(Path.of(filepath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                notesArray = gson.fromJson(reader, Note[].class);
            } catch (IOException e) {
                throw new JSONReadException("Fehler beim Lesen der JSON-Datei: Die Datei \"notes.json\" konnte nicht gelesen werden. Stellen Sie sicher, dass die Datei vorhanden ist und die erforderlichen Zugriffsrechte vorliegen.\n");
            }
        }
        if (notesArray == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(notesArray));
    }

    public void storeNote(String filePath, Note note) throws JSONWriteException {
        if (note != null && notesTookByUser != null) {
            notesTookByUser.add(note);
            storeNotes(filePath);
        } else {
            throw new JSONWriteException("Notes couldn't be accessed");
        }

    }

    public void deleteNote(String filePath, Note note) throws JSONWriteException {
        if (notesTookByUser.contains(note)) {
            notesTookByUser.remove(note);
            storeNotes(filePath);
        } else {
            throw new IllegalArgumentException("Notiz ist nicht in der JSON-Datei");
        }

    }

    private void storeNotes(String filePath) throws JSONWriteException {
        checkIfFileExists();
        try (FileWriter writer = new FileWriter(filePath)) {
            if (!notesTookByUser.isEmpty()) {
                writer.write("[");
                int i = 1;
                for (Note note : notesTookByUser) {
                    writer.write(note.noteToGson());
                    if (notesTookByUser.size() > i) {
                        writer.write(",");
                    }
                }
                writer.write("]");
            }
            writer.write("");
        } catch (IOException e) {
            throw new JSONWriteException(e.getMessage());
        }
    }

    private void checkIfFileExists() {
        if (!Files.exists(Path.of(filepath))) {
            new File(filepath).mkdirs();
        }
        if (!Files.exists(Path.of(userFilePath))) {
            createNewFIle();
        }
    }

    private void createNewFIle() {
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showView() {
        noteView.show();
    }

    public void exit() {
        overviewView.show();
    }
}
