package de.dhbw.cm.presentation;

import de.dhbw.cm.domain.Month;
import de.dhbw.cm.domain.Weekday;

import java.util.Map;

public class CalendarView {

    public void printMonth(int year, Month month, Map<Integer, Weekday> weekdaysMap) {

        StringBuilder calenderLine = new StringBuilder();
        printHeadOfCalender(year, month);
        calenderLine.append(buildUpFiller(weekdaysMap.get(1)));
        int iterateIndex = 1;
        for (Map.Entry<Integer, Weekday> dateWithWeekday : weekdaysMap.entrySet()) {
            if (dateWithWeekday.getValue().equals(Weekday.SATURDAY)) {
                System.out.println(calenderLine);
                calenderLine.setLength(0);
            }

            if (iterateIndex < 10) {
                calenderLine.append("0"); // Fügt eine führende Null für einstellige Zahlen hinzu
            }
            calenderLine.append(dateWithWeekday.getKey()).append(" ");
            iterateIndex++;
        }
        System.out.println(calenderLine);
    }

    private StringBuilder buildUpFiller(Weekday weekday) {
        String filler = "   ";
        StringBuilder fillerBuilded = new StringBuilder();
        int numberOfFillers = weekday.ordinal() - Weekday.SATURDAY.ordinal();
        for (int i = 0; i < numberOfFillers; i++) {
            fillerBuilded.append(filler);
        }
        return fillerBuilded;
    }

    private void printHeadOfCalender(int year, Month month) {
        StringBuilder calenderLine = new StringBuilder();
        calenderLine.append(month).append("   ").append(year);
        System.out.println(calenderLine);
        calenderLine.setLength(0);
        for (Weekday weekday : Weekday.values()) {
            calenderLine.append(weekday.getShortcut()).append(" ");
        }
        System.out.println(calenderLine);
    }

    ;
}
