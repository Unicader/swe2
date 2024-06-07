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
        noteView = new NoteView(noteManagerApp, cw, cr, FILE_PATH);
    }


}
