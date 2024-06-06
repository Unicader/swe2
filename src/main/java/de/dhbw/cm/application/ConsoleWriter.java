package de.dhbw.cm.application;


public class ConsoleWriter {

    public ConsoleWriter() {
    }

    public void write(String message) {
        System.out.println(message);
    }

    public void write(AnsiCodes color, String message) {
        System.out.println(color.getCode() + message + AnsiCodes.RESET.getCode());
    }

    public void writeInLine(String message) {
        System.out.print(message);
    }

    public void writeInLine(AnsiCodes color, String message) {
        System.out.print(color.getCode() + message + AnsiCodes.RESET.getCode());
    }

    public void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch (Exception ignored) {
        }
    }
}
