package de.dhbw.cm.application;
import de.dhbw.cm.logging.JSONReadException;
import de.dhbw.cm.logging.JSONWriteException;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteManagerAppTest {

    public static final String FILE_PATH = "src/test/java/resources/notes" +
            ".json";

    @Test
    public void testReturnStoredNotes() throws IOException, JSONReadException {
        Note note = new Note("Test", "Test note", new Date(1,2999,
                Month.DECEMBER), Priority.HIGH);

        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write("[");
        fileWriter.write(note.noteToGson());
        fileWriter.write("]");
        fileWriter.close();

        NoteManagerApp noteManagerApp = new NoteManagerApp(FILE_PATH);
        List<Note> notes = noteManagerApp.returnStoredNotes(FILE_PATH);

        assertEquals(1, notes.size());
        assertEquals("Test", notes.get(0).getTitel());
        assertEquals("Test note", notes.get(0).getNotes());
    }

    @Test
    public void testStoreNote() throws JSONWriteException, JSONReadException {
        Note note = new Note("Meeting", "Discuss project status", Priority.HIGH);
        NoteManagerApp noteManagerApp = new NoteManagerApp(FILE_PATH);
        noteManagerApp.storeNote(FILE_PATH, note);
    }
}
