package de.dhbw.cm.application;

import java.util.Scanner;

public class ConsoleReader {
    Scanner scanner;
    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
