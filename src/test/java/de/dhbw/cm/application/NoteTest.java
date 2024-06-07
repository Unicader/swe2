package de.dhbw.cm.application;
import com.google.gson.Gson;
import de.dhbw.cm.domain.Date;
import de.dhbw.cm.domain.Month;
import de.dhbw.cm.domain.Note;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {

    @Test
    public void testToString_AllFieldsPresent() {
        String titel = "Test Titel";
        String notes = "Test Notes";
        Date date = new Date(31, 2099, Month.DECEMBER);
        Priority priority = Priority.HIGH;

        Note note = new Note(titel, notes, date, priority);
        String result = note.toString();

        String expected = "Titel:Test Titel\n" +
                "Note:Test Notes\n" +
                "date:31.12.2099\n" +
                "Priority:HIGH\n";
        assertEquals(expected, result);
    }

    @Test
    public void testToString_EmptyFields() {
        String titel = "";
        String notes = "";
        Date date = new Date(1, 1970, Month.JANUARY);
        Priority priority = Priority.LOW;

        Note note = new Note(titel, notes, date, priority);

        String result = note.toString();

        String expected = "Titel:\n" +
                "Note:\n" +
                "date:01.01.1970\n" +
                "Priority:LOW\n";
        assertEquals(expected, result);
    }

    @Test
    public void testToString_NullDate() {
        String titel = "Test Titel";
        String notes = "Test Notes";
        Date date = null;
        Priority priority = Priority.MEDIUM;

        Note note = new Note(titel, notes, date, priority);

        String result = note.toString();

        String expected = "Titel:Test Titel\n" +
                "Note:Test Notes\n" +
                "date:null\n" +
                "Priority:MEDIUM\n";
        assertEquals(expected, result);
    }

    @Test
    public void testToString_NullFields() {
        String titel = null;
        String notes = null;
        Date date = null;
        Priority priority = null;

        Note note = new Note(titel, notes, date, priority);

        String result = note.toString();

        String expected = "Titel:null\n" +
                "Note:null\n" +
                "date:null\n" +
                "Priority:null\n";
        assertEquals(expected, result);
    }

    @Test
    public void testGeneratePseudeDate() {
        String titel = "Test Titel";
        String notes = "Test Notes";
        Priority priority = Priority.HIGH;

        Note note = new Note(titel, notes, priority);

        Date result = note.getDate();

        Date expected = new Date(31, 2099, Month.DECEMBER);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void testNoteToGson() {
        String titel = "Meeting";
        String notes = "Discuss project status";
        Date date = new Date(1, 2000, Month.DECEMBER);
        Priority priority = Priority.HIGH;

        Note note = new Note(titel, notes, date, priority);
        String json = note.noteToGson();

        Gson gson = new Gson();
        Note deserializedNote = gson.fromJson(json, Note.class);

        assertEquals(note.getTitel(), deserializedNote.getTitel());
        assertEquals(note.getNotes(), deserializedNote.getNotes());
        assertEquals(note.getDate(), deserializedNote.getDate());
        assertEquals(note.getPriority(), deserializedNote.getPriority());
    }
}
