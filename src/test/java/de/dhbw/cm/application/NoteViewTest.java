package de.dhbw.cm.application;

import de.dhbw.cm.domain.Date;
import de.dhbw.cm.domain.Month;
import de.dhbw.cm.domain.Note;
import de.dhbw.cm.infrastructure.repository.NoteManagerApp;
import de.dhbw.cm.presentation.ConsoleReader;
import de.dhbw.cm.presentation.ConsoleWriter;
import de.dhbw.cm.presentation.NoteView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteViewTest {

    @Mock
    private ConsoleWriter cw;
    @Mock
    private ConsoleReader cr;

    @Mock
    private NoteManagerApp noteManagerApp;

    private NoteView noteView;
    private List<Note> notes;
    public static final String FILE_PATH = "src/test/java/resources/notes" +
            ".json";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notes = spy(new ArrayList<>()); // Verwenden Sie eine echte Liste
        noteView = new NoteView(noteManagerApp, notes, cw, cr, FILE_PATH);
    }

    @Test
    void testGetNotes() {
        List<Note> expectedNotes = List.of(new Note("Title1", "Note1", new Date(1, 2024, Month.JANUARY), Priority.HIGH),
                new Note("Title2", "Note2", new Date(2, 2024, Month.FEBRUARY), Priority.LOW));
        notes.addAll(expectedNotes);
        List<Note> result = noteView.getNotes();
        assertEquals(expectedNotes, result);
    }

    @Test
    void testPrintNotes() {
        Note note1 = new Note("Title1", "Note1", new Date(1, 2024, Month.JANUARY), Priority.HIGH);
        Note note2 = new Note("Title2", "Note2", new Date(2, 2024, Month.FEBRUARY), Priority.LOW);
        notes.add(note1);
        notes.add(note2);
        noteView.printNotes();
        verify(cw, times(1)).write("NoteIndex: 1\n" + note1.toString());
        verify(cw, times(1)).write("NoteIndex: 2\n" + note2.toString());
    }

}
