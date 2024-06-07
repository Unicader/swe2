package de.dhbw.cm.application;
import de.dhbw.cm.domain.Weekday;
import de.dhbw.cm.presentation.ConsoleWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerAppTest {
    private CalendarManagerApp calendarManager;

    @BeforeEach
    void setUp() {
        calendarManager = new CalendarManagerApp(2024, 3, new ConsoleWriter()); // March 2024
    }

    @Test
    void testIsLeapYear() {
        assertTrue(calendarManager.isLeapYear());
    }

    @Test
    void testIsLeapYear2000() {
        assertFalse(new CalendarManagerApp(2100, 3, new ConsoleWriter()).isLeapYear());
    }

    @Test
    void testGetWeekdayPerDay() {
        Map<Integer, Weekday> weekdaysMap = calendarManager.getWeekdayPerDay();
        assertNotNull(weekdaysMap);
        assertEquals(31, weekdaysMap.size()); // March 2024 has 31 days
    }

    @Test
    void testGetDaysInMonth() {
        assertEquals(31, calendarManager.getDaysInMonth());
    }

    @Test
    void testGetDaysInMonthLeapYear() {
        CalendarManagerApp feb2024 = new CalendarManagerApp(2024, 2, new ConsoleWriter());
        assertEquals(29, feb2024.getDaysInMonth());
    }

    @Test
    void getDaysInMonthNonLeapYear() {
        CalendarManagerApp feb2023 = new CalendarManagerApp(2023, 2, new ConsoleWriter());
        assertEquals(28, feb2023.getDaysInMonth());
    }

    @Test
    void testCalculateWeekday() {
        assertEquals(Weekday.FRIDAY, calendarManager.calculateWeekday(1, 3, 2024)); // March 1, 2024 is Thursday
        assertEquals(Weekday.THURSDAY, calendarManager.calculateWeekday(29, 2, 2024)); // February 29, 2024 is Friday (Leap Year)
        assertEquals(Weekday.TUESDAY, calendarManager.calculateWeekday(30, 1, 2024)); // January 30, 2024 is Saturday
        assertEquals(Weekday.SUNDAY, calendarManager.calculateWeekday(31, 12, 2023)); // December 31, 2023 is Sunday
    }
}