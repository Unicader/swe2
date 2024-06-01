package de.dhbw.cm.application;

import de.dhbw.cm.domain.User;

public class OverviewView {
    private final User user;
    private final String username;
    private final ConsoleWriter cw;
    private final ConsoleReader cr;

    public OverviewView(User user, String username, ConsoleWriter consoleWriter, ConsoleReader consoleReader) {
        this.user = user;
        this.username = username;
        this.cw = consoleWriter;
        this.cr = consoleReader;
    }

    public void show() {
        cw.clearConsole();
        cw.write(AnsiCodes.CYAN, "Welcome " + username + "!!!");
    }
}
