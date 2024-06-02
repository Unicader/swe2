package de.dhbw.cm.application;

import java.util.List;

public class NoteView {
    private List<Note> notes;
    private ConsoleWriter consoleWriter;
    public NoteView(List<Note> notes, ConsoleWriter consoleWriter) {
        this.notes = notes;
        this.consoleWriter = consoleWriter;
    }
    public List<Note> getNotes() {
        return notes;
    }

    public void printNotes() {
        for (Note note : notes) {
            consoleWriter.write(note.toString());
        }
    }
}
