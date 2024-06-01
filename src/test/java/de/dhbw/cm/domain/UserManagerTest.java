package de.dhbw.cm.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {

    private final String FILE_PATH = "testUsers.json";
    private UserManager userManager;

    @BeforeEach
    void setUp() {
        userManager = new UserManager(FILE_PATH);
    }

    @AfterEach
    void tearDown() {
        // Clean up test file
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testCreateUser() {
        assertTrue(userManager.createUser("testUser", "password123"));
    }

    @Test
    void testCreateDuplicateUser() {
        userManager.createUser("testUser", "password123");
        assertFalse(userManager.createUser("testUser", "password123"));
    }

    @Test
    void testLoginWithCorrectCredentials() {
        userManager.createUser("testUser", "password123");
        assertNotNull(userManager.loginUser("testUser", "password123"));
    }

    @Test
    void testLoginWithIncorrectPassword() {
        userManager.createUser("testUser", "password123");
        assertNull(userManager.loginUser("testUser", "wrongPassword"));
    }

    @Test
    void testLoginWithNonExistingUser() {
        assertNull(userManager.loginUser("nonExistingUser", "password123"));
    }
}
