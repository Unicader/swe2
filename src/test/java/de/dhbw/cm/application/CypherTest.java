package de.dhbw.cm.application;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

public class CypherTest {

    @Test
    public void testEncryptionDecryption() {
        try {
            String originalText = "Hello, World!";
            SecretKey secretKey = Cypher.generateKey(128);

            String encryptedText = Cypher.encrypt(originalText, secretKey);
            String decryptedText = Cypher.decrypt(encryptedText, secretKey);

            assertEquals(originalText, decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testDifferentKeys() {
        try {
            String originalText = "Hello, World!";
            SecretKey secretKey1 = Cypher.generateKey(128);
            SecretKey secretKey2 = Cypher.generateKey(128);

            String encryptedText = Cypher.encrypt(originalText, secretKey1);

            assertThrows(Exception.class, () -> {
                Cypher.decrypt(encryptedText, secretKey2);
            });
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testEmptyString() {
        try {
            String originalText = "";
            SecretKey secretKey = Cypher.generateKey(128);

            String encryptedText = Cypher.encrypt(originalText, secretKey);
            String decryptedText = Cypher.decrypt(encryptedText, secretKey);

            assertEquals(originalText, decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testLongString() {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                sb.append("Hello, World! ");
            }
            String originalText = sb.toString();
            SecretKey secretKey = Cypher.generateKey(128);

            String encryptedText = Cypher.encrypt(originalText, secretKey);
            String decryptedText = Cypher.decrypt(encryptedText, secretKey);

            assertEquals(originalText, decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testSpecialCharacters() {
        try {
            String originalText = "こんにちは、世界！";
            SecretKey secretKey = Cypher.generateKey(128);

            String encryptedText = Cypher.encrypt(originalText, secretKey);
            String decryptedText = Cypher.decrypt(encryptedText, secretKey);

            assertEquals(originalText, decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }
}
