package de.dhbw.cm.presentation;

import de.dhbw.cm.application.CalendarManagerApp;

public class CalenderOverviewView {
    final ConsoleWriter cw;
    final ConsoleReader cr;
    final OverviewView overviewView;
    private int currentYear;
    private int currentMonthIndex;

    public CalenderOverviewView(ConsoleWriter consoleWriter, ConsoleReader consoleReader, OverviewView overviewView) {
        this.cw = consoleWriter;
        this.cr = consoleReader;
        this.overviewView = overviewView;
        this.currentYear = java.time.Year.now().getValue();
        this.currentMonthIndex = java.time.LocalDate.now().getMonthValue();
    }

    public void show() {
        while (true) {
            cw.clearConsole();
            CalendarManagerApp calendarManagerApp = new CalendarManagerApp(currentYear, currentMonthIndex, cw);
            calendarManagerApp.printMonth();
            cw.write(AnsiCodes.YELLOW, "\nEnter 'n' for next month, 'p' for previous month, or 'b' to go back: ");
            String input = cr.readLine().trim().toLowerCase();

            if (input.equals("b")) {
                overviewView.show();
                break;
            } else if (input.equals("n")) {
                nextMonth();
            } else if (input.equals("p")) {
                previousMonth();
            } else {
                cw.write(AnsiCodes.RED, "\nInvalid input. Please try again.\n");
            }
        }
    }

    private void nextMonth() {
        if (currentMonthIndex == 12) {
            currentMonthIndex = 1;
            currentYear++;
        } else {
            currentMonthIndex++;
        }
    }

    private void previousMonth() {
        if (currentMonthIndex == 1) {
            currentMonthIndex = 12;
            currentYear--;
        } else {
            currentMonthIndex--;
        }
    }
}
