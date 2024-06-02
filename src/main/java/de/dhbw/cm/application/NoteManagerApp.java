package de.dhbw.cm.application;

import com.google.gson.Gson;
import de.dhbw.cm.logging.JSONReadException;
import de.dhbw.cm.logging.JSONWriteException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteManagerApp {
    List<Note> notesTookByUser = new ArrayList<>();

    public List<Note> returnStoredNotes(String filepath) throws JSONReadException {
        List<Note> notes = new ArrayList<>();
        Gson gson = new Gson();
        Note[] notesArray = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            notesArray = gson.fromJson(reader, Note[].class);
        } catch (IOException e) {
            throw new JSONReadException("Fehler beim Lesen der JSON-Datei: Die Datei \"notes.json\" konnte nicht gelesen werden. Stellen Sie sicher, dass die Datei vorhanden ist und die erforderlichen Zugriffsrechte vorliegen.\n");
        }
        return List.of(notesArray);
    }

    public void storeNote(String filePath, Note note) throws JSONWriteException {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw")) {
            long length = randomAccessFile.length();
            if (length == 0) {
                randomAccessFile.writeBytes("[\n");
                randomAccessFile.writeBytes(note.noteToGson());
                randomAccessFile.writeBytes("\n]");
            } else {
                randomAccessFile.seek(length - 1);
                byte lastByte = randomAccessFile.readByte();
                if (lastByte == ']') {
                    randomAccessFile.seek(length - 1);
                    randomAccessFile.writeBytes(",\n");
                    randomAccessFile.writeBytes(note.noteToGson());
                    randomAccessFile.writeBytes("\n]");
                } else {
                    randomAccessFile.seek(length);
                    randomAccessFile.writeBytes(",\n");
                    randomAccessFile.writeBytes(note.noteToGson());
                    randomAccessFile.writeBytes("\n]");
                }
            }
        } catch (IOException e) {
            throw new JSONWriteException("Fehler beim Schreiben der " +
                    "JSON-Datei: Die Datei \"notes.json\" konnte nicht " +
                    "beschrieben oder gefunden" +
                    " werden. ");
        }

    }
}
