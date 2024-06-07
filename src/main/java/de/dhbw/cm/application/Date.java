package de.dhbw.cm.application;

import java.util.Objects;
import java.util.Set;

public class Date {
    private final int day;
    private final int year;
    private final Month month;

    private final static Set<Integer> THIRTY_DAY_MONTHS = Set.of(4, 6, 9, 11);

    public Date(int day, int year, Month month) {
        this.day = day;
        this.year = year;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    @Override
    public String toString() {
        String dayInString = getDayInString();
        return dayInString + "." + getMonth().getMonthNumber() + "." + getYear();
    }

    private String getDayInString() {
        return String.format("%02d", getDay());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date date = (Date) obj;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    public static Date getDate(int day, int month, int year) {
        Month selectedMonth = getMonth(month);
        if (selectedMonth == null && !checkIfDateIaValid(day, month, year)){
            throw new IllegalArgumentException("Date is not valid");
        }
        return new Date(day, year, selectedMonth);
    }

    public static Month getMonth(int month) {
        Month[] values = Month.values();
        Month selectedMonth = null;
        String monthAsString = "";
        if (month > 0 && month < 10) {
            monthAsString = "0" + month;
        }else {
            monthAsString = String.valueOf(month);
        }
        for (Month value : values) {
            if (value.getMonthNumber().equals(monthAsString)) {
                selectedMonth = value;
            }
        }
        return selectedMonth;
    }

    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }

    private static boolean checkIfDateIaValid(int day, int month, int year){
        int daysInMonth = daysInMonth(month, year);
        return daysInMonth >= day;

    }
    private static int daysInMonth(int month, int year) {
        if (isThirtyDayMonth(month)) {
            return 30;
        } else if (isFebruary(month)) {
            return getDaysInFebruary(year);
        } else {
            return 31;
        }
    }

    private static boolean isThirtyDayMonth(int monthIndex) {
        return THIRTY_DAY_MONTHS.contains(monthIndex);
    }

    private static boolean isFebruary(int monthIndex) {
        return monthIndex == 2;
    }

    private static int getDaysInFebruary(int year) {
        return isLeapYear(year) ? 29 : 28;
    }

}
