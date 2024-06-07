package de.dhbw.cm.application;

import de.dhbw.cm.logging.JSONReadException;
import de.dhbw.cm.logging.JSONWriteException;

import java.util.List;
import java.util.regex.Pattern;

public class NoteView {
    private final ConsoleReader consoleReader;
    private List<Note> notes;
    private final ConsoleWriter consoleWriter;
    private NoteManagerApp noteManagerApp;
    private String filepath;

    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");


    public NoteView(NoteManagerApp noteManagerApp, List<Note> notes,
                    ConsoleWriter consoleWriter,
                    ConsoleReader consoleReader, String filepath) {
        this.noteManagerApp = noteManagerApp;
        this.notes = notes;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        this.filepath = filepath;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void printNotes() {
        int index = 1;
        if (notes != null && !notes.isEmpty()) {
            for (Note note : notes) {
                String consoleOutput = "NoteIndex: " + index + "\n";
                consoleWriter.write(consoleOutput + note.toString());
                index++;
            }
        } else {
            consoleWriter.write("No Notes Found\n");
        }
    }

    public void show() {
        consoleWriter.clearConsole();
        showMenu();
    }

    void validateInput() {
        String choice = consoleReader.readLine();
        if (choice.equals("1")) {
            getOverview();
            showMenu();
        } else if (choice.equals("2")) {
            createNote();
            showMenu();
        } else if (choice.equals("3")) {
            getOverview();
            int noteNumber = getNoteNumber();
            if (noteNumber > 0 && noteNumber <= notes.size()) {
                deleteNote(notes.get(noteNumber - 1));
            }
            showMenu();
        } else if (choice.equals("4")) {
            exit();
        } else {
            consoleWriter.write(AnsiCodes.RED, "\nInvalid choice. Please try again.\n");
            validateInput();
        }
    }

    private void exit() {
        this.noteManagerApp.exit();
    }

    private int getNoteNumber() {
        consoleWriter.write(AnsiCodes.YELLOW, "Please enter a Note " +
                "number:");
        String noteNumber = consoleReader.readLine();
        if (noteNumber != null) {
            try {
                return Integer.parseInt(noteNumber);
            } catch (Exception e) {
                consoleWriter.write(AnsiCodes.RED, "Note number is not a valid integer.\n");
            }
        }
        return 0;
    }

    private void createNote() {
        consoleWriter.write(AnsiCodes.YELLOW, "\n Choose a titel\n");
        String title = consoleReader.readLine();
        consoleWriter.write(AnsiCodes.YELLOW, "\n Choose a Note\n");
        String note = consoleReader.readLine();


        Date date = dateInput();

        Priority prio = prioInput();

        try {
            noteManagerApp.storeNote(filepath, new Note(title, note, date, prio));
        } catch (JSONWriteException e) {
            throw new RuntimeException(e);
        }
    }

    private Date dateInput() {
        consoleWriter.write(AnsiCodes.YELLOW, "\n Choose a date: Format " +
                "dd-mm-yyyy\n");
        String input = consoleReader.readLine();
        boolean matches = DATE_PATTERN.matcher(input).matches();
        Date date = null;
        if (matches) {
            String[] splittedInput = input.split("-");
            int day = Integer.parseInt(splittedInput[0]);
            int month = Integer.parseInt(splittedInput[1]);
            int year = Integer.parseInt(splittedInput[2]);
            date = Date.getDate(day, month, year);
            return date;
        }
        dateInput();
        return date;
    }

    private Priority prioInput() {
        boolean prioInputIsValid = true;
        Priority prioAsEnum = Priority.LOW;
        do {
            consoleWriter.write(AnsiCodes.YELLOW, "\n Choose a Priority. LOW == 1, MEDIUM == 2, HIGH == 3\n");
            String prio = consoleReader.readLine();
            prioInputIsValid = true;
            if (prio.equals("1")) {
            }
            else if (prio.equals("2")) {
                prioAsEnum = Priority.MEDIUM;
            } else if (prio.equals("3")) {
                prioAsEnum = Priority.HIGH;
            } else {
                consoleWriter.write(AnsiCodes.RED, "\nInvalid choice. Please try again.\n");
                prioInputIsValid = false;
            }
        } while (!prioInputIsValid);
        return prioAsEnum;
    }


    private void deleteNote(Note note) {
        try {
            noteManagerApp.deleteNote(filepath, note);
            consoleWriter.write(AnsiCodes.YELLOW, "\n Note deleted\n");
        } catch (JSONWriteException e) {
            throw new RuntimeException(e);
        }

    }

    private void getOverview() {
        try {
            notes = noteManagerApp.returnStoredNotes(filepath);
            this.printNotes();
        } catch (JSONReadException e) {
            throw new RuntimeException(e);
        }
    }

    private void showMenu() {
        consoleWriter.write(AnsiCodes.YELLOW,
                "Choose an option:\n" +
                        "\t1. get Overview\n" +
                        "\t2. create new Note\n" +
                        "\t3. delete Note\n" +
                        "\t4. Exit\n");
        validateInput();
    }
}
