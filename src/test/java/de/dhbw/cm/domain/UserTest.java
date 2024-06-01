package de.dhbw.cm.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testCheckPassword() {
        User user = new User("testUser", "password123");

        assertTrue(user.checkPassword("password123"));
        assertFalse(user.checkPassword("wrongPassword"));
    }
}