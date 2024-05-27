package de.dhbw.cm.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalendarViewTest {
    private CalendarManagerApp calendarManager;

    @BeforeEach
    void setUp() {
        calendarManager = new CalendarManagerApp(2024, 2); // March 2024
    }

    @Test
    void testOutput() {
        calendarManager.printMonth();
        assert true;
    }
}