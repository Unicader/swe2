package de.dhbw.cm.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static de.dhbw.cm.application.Weekday.*;

public class CalendarManagerApp {

    private final int year;
    private final int monthIndex;
    private final Month month;
    private final Map<Integer, Weekday> weekdaysMap = new HashMap<>();
    private final CalendarView calendarView;
    private final Set<Integer> THIRTY_DAY_MONTHS = Set.of(4, 6, 9, 11);

    public CalendarManagerApp(int year, int monthIndex) {
        this.year = year;
        if (monthIndex < 1 || monthIndex > 12) {
            throw new IllegalArgumentException("Month index must be between 1 and 12");
        }
        this.monthIndex = monthIndex;
        month = Month.values()[monthIndex - 1];
        getWeekdayPerDay();
        calendarView = new CalendarView();
    }

    public void printMonth() {
        calendarView.printMonth(year, month, weekdaysMap);
    }

    public boolean isLeapYear() {
        return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }

    public Map<Integer, Weekday> getWeekdayPerDay() {

        for (int day = 1; day <= getDaysInMonth(); day++) {
            Weekday weekday = calculateWeekday(day, monthIndex, year);
            weekdaysMap.put(day, weekday);
        }

        return weekdaysMap;
    }

    int getDaysInMonth() {
        if (isThirtyDayMonth()) {
            return 30;
        } else if (isFebruary()) {
            return getDaysInFebruary();
        } else {
            return 31;
        }
    }

    private boolean isThirtyDayMonth() {
        return THIRTY_DAY_MONTHS.contains(monthIndex);
    }

    private boolean isFebruary() {
        return monthIndex == 2;
    }

    private int getDaysInFebruary() {
        return isLeapYear() ? 29 : 28;
    }

    Weekday calculateWeekday(int day, int month, int year) {
        if (month < 3) {
            month += 12;
            year--;
        }
        int k = year % 100;
        int j = year / 100;
        int h = (day + 13 * (month + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;
        Weekday[] weekdays = {SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
        return weekdays[h];
    }
}
