package de.dhbw.cm.domain;

public enum Weekday{
    SATURDAY("SA"), SUNDAY("SU"), MONDAY("MO"), TUESDAY("TU"), WEDNESDAY("WE"), THURSDAY("TH"), FRIDAY("FR");

    private final String shortcut;

    Weekday(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }
}
