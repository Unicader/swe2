package de.dhbw.cm.main;

import de.dhbw.cm.application.ConsoleReader;
import de.dhbw.cm.application.ConsoleWriter;
import de.dhbw.cm.application.LoginView;

public class Main {
    public static void main(String[] args) {
        ConsoleWriter consoleWriter = new ConsoleWriter();
        ConsoleReader consoleReader = new ConsoleReader();
        //Login
        LoginView loginView = new LoginView(consoleWriter, consoleReader);
        loginView.show();
    }
}
