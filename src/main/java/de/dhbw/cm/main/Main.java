package de.dhbw.cm.main;

import de.dhbw.cm.presentation.ConsoleReader;
import de.dhbw.cm.presentation.ConsoleWriter;
import de.dhbw.cm.presentation.LoginView;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView(new ConsoleWriter(), new ConsoleReader());
        loginView.show();
    }
}
