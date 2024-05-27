package de.dhbw.cm.application;

import java.lang.annotation.Documented;
import java.time.Month;

public class Note {
    private String titel;
    private String notes;
    private Date date;
    private Priority priority;

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
}
