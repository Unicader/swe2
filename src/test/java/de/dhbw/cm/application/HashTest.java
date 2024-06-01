package de.dhbw.cm.application;

import de.dhbw.cm.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTest {

    @Test
    void testHashFunction() {
        String hashedPassword = Hash.hashFunction("123");

        assertEquals("pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=", hashedPassword);
    }
}
