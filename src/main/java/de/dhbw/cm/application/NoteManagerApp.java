package de.dhbw.cm.application;
import com.google.gson.Gson;
import de.dhbw.cm.logging.JSONReadException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class NoteManagerApp {
   List<Note> notesTookByUser = new ArrayList<>();

    public static void main(String[] args) {
        try {
            new NoteManagerApp().returnStoredNotes();
        } catch (JSONReadException e) {
            throw new RuntimeException(e);
        }
    }
   private List<Note> returnStoredNotes() throws JSONReadException {
       List<Note> notes = new ArrayList<>();
       Gson gson = new Gson();
       Note[] notesArray = null;
       try (Reader reader = new FileReader("src/main/java/resources/notes.json")) {
            notesArray = gson.fromJson(reader, Note[].class);
       } catch (IOException e) {
           throw new JSONReadException("Fehler beim Lesen der JSON-Datei: Die Datei \"notes.json\" konnte nicht gelesen werden. Stellen Sie sicher, dass die Datei vorhanden ist und die erforderlichen Zugriffsrechte vorliegen.\n");
       }
       return List.of(notesArray);
   }

}
