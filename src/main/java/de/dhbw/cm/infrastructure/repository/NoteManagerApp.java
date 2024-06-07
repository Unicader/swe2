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
import java.util.ArrayList;
import java.util.List;


public class NoteManagerApp {
    final String filepath = "src/main/java/resources/";
    List<Note> notesTookByUser = new ArrayList<>();
    NoteView noteView;
    OverviewView overviewView;

    public NoteManagerApp(String username, ConsoleReader consoleReader, ConsoleWriter consoleWriter, OverviewView overviewView) {
        this.overviewView = overviewView;
        try {
            String userFilePath = filepath + username;
            notesTookByUser = returnStoredNotes(userFilePath);
            noteView = new NoteView(this, notesTookByUser, consoleWriter,
                    consoleReader, userFilePath);
            noteView.show();
        } catch (JSONReadException e) {
            throw new RuntimeException(e);
        }
    }

    public NoteManagerApp(String filepath) throws JSONReadException {
        noteView = new NoteView(this, returnStoredNotes(filepath),
                new ConsoleWriter(), new ConsoleReader(), filepath);
    }

    public List<Note> returnStoredNotes(String filepath) throws JSONReadException {
        List<Note> notes = new ArrayList<>();
        Gson gson = new Gson();
        Note[] notesArray;
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            notesArray = gson.fromJson(reader, Note[].class);
        } catch (IOException e) {
            throw new JSONReadException("Fehler beim Lesen der JSON-Datei: Die Datei \"notes.json\" konnte nicht gelesen werden. Stellen Sie sicher, dass die Datei vorhanden ist und die erforderlichen Zugriffsrechte vorliegen.\n");
        }
        return List.of(notesArray);
    }

    public void storeNote(String filePath, Note note) throws JSONWriteException {
        notesTookByUser.add(note);
        storeNotes(filePath);
    }

    public void deleteNote(String filePath, Note note) throws JSONWriteException {
        if (notesTookByUser.contains(note)) {
            notesTookByUser.remove(note);
            storeNote(filePath, note);
        } else {
            throw new IllegalArgumentException("Notiz ist nicht in der JSON-Datei");
        }

    }

    private void storeNotes(String filePath) throws JSONWriteException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("[");
            for (Note note : notesTookByUser) {
                writer.write(note.noteToGson());
            }
            writer.write("]");
        } catch (IOException e) {
            throw new JSONWriteException(e.getMessage());
        }
    }
}
