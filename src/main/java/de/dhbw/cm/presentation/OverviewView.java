package de.dhbw.cm.presentation;

import de.dhbw.cm.application.CalendarManagerApp;
import de.dhbw.cm.infrastructure.repository.NoteManagerApp;
import de.dhbw.cm.domain.User;

public class OverviewView {
    private final User user;
    private final String username;
    private final ConsoleWriter cw;
    private final ConsoleReader cr;
    private final LoginView loginView;

    public OverviewView(User user, String username, ConsoleWriter consoleWriter, ConsoleReader consoleReader,
                        LoginView loginView) {
        this.user = user;
        this.username = username;
        this.cw = consoleWriter;
        this.cr = consoleReader;
        this.loginView = loginView;
    }

    public void show() {
        cw.clearConsole();
        cw.write(AnsiCodes.CYAN, "Welcome " + username + "!!!");
        cw.write(AnsiCodes.YELLOW,
                "\nChoose an option:\n" +
                        "\t1. Notes\n" +
                        "\t2. Calender\n" +
                        "\t3. Logout\n" +
                        "\t4. Exit\n");
        validateInput();
    }

    void validateInput() {
        String choice = cr.readLine();
        if (choice.equals("1")) {
            new NoteManagerApp(user.getUsername(), cr, cw, this);
        } else if (choice.equals("2")) {
            //TODO show calender
            CalenderOverviewView calenderOverviewView = new CalenderOverviewView(cw, cr, this);
            calenderOverviewView.show();
        } else if (choice.equals("3")) {
            loginView.show();
        } else if (choice.equals("4")) {
            cw.write(AnsiCodes.YELLOW, "\nExiting...");
        } else {
            cw.write(AnsiCodes.RED, "\nInvalid choice. Please try again.\n");
            validateInput();
        }
    }
}