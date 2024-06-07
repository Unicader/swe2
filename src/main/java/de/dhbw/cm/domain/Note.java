package de.dhbw.cm.domain;

import com.google.gson.Gson;
import de.dhbw.cm.application.Priority;

public class Note {
    private final String titel;
    private final String notes;
    private Date date;
    private final Priority priority;

    public Note(String titel, String notes, Priority priority) {
        this.titel = titel;
        this.notes = notes;
        this.priority = priority;
        this.date = generatePseudeDate();
    }

    private Date generatePseudeDate() {
        return new Date(31, 2099, Month.DECEMBER);
    }

    public Note(String titel, String notes, Date date, Priority priority) {
        this(titel, notes, priority);
        this.date = date;
    }

    public String getTitel() {
        return titel;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDate() {
        return date;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String lineBreak = "\n";
        sb.append("Titel:").append(titel).append(lineBreak);
        sb.append("Note:").append(notes).append(lineBreak);
        sb.append("date:").append(date).append(lineBreak);
        sb.append("Priority:").append(priority).append(lineBreak);
        return sb.toString();
    }

    public String noteToGson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Note note = (Note) obj;
        return titel.equals(note.titel) && notes.equals(note.notes) && priority.equals(note.priority) && date.equals(note.date);
    }
}
