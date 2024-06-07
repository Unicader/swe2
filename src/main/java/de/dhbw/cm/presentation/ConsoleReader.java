package de.dhbw.cm.presentation;

import java.util.Scanner;

public class ConsoleReader {
    final Scanner scanner;
    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
