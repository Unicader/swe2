package de.dhbw.cm.application;

public class Note {
    private String titel;
    private String notes;
    private Date date;
    private Priority priority;

    public Note(String titel, String notes, Priority priority) {
        this.titel = titel;
        this.notes = notes;
        this.priority = priority;
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
}
