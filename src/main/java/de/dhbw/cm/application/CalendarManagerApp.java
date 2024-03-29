package de.dhbw.cm.application;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.HashMap;
import java.util.Map;

import static de.dhbw.cm.application.Weekday.*;

public class CalendarManagerApp {

    private int year;
    private int monthIndex;
    private Month month;
    private Map<Integer, Weekday> weekdaysMap = new HashMap<>();
    private CalendarView calendarView;
    public CalendarManagerApp(int year, int monthIndex) {
        this.year = year;
        this.monthIndex = monthIndex;
        month = Month.values()[monthIndex + 1];
        getWeekdayPerDay();
        calendarView = new CalendarView();
    }

    public void printMonth(){
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
        if (monthIndex == 4 || monthIndex == 6 || monthIndex == 9 || monthIndex == 11){
            return 30;
        } else if (monthIndex == 2) {
            return isLeapYear() ? 29 : 28;
        } else {
            return 31;
        }
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
