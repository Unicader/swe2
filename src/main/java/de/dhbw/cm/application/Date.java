package de.dhbw.cm.application;

import java.time.Month;

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
}
