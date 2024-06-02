package de.dhbw.cm.application;

import java.util.Objects;

public class Date {
    private int day;
    private int year;
    private Month month;

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
}
