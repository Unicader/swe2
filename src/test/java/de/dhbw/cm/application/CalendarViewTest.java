package de.dhbw.cm.application;

import de.dhbw.cm.presentation.ConsoleWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalendarViewTest {
    private CalendarManagerApp calendarManager;

    @BeforeEach
    void setUp() {
        calendarManager = new CalendarManagerApp(2024, 4, new ConsoleWriter());
    }

    @Test
    void testOutput() {
        calendarManager.printMonth();
        assert true;
    }
}