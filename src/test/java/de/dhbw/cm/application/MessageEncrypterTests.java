package de.dhbw.cm.application;

import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageEncrypterTests {
    @Test
    void testAESEncryption() throws Exception {
        SecretKey aesKey = KeyGenerator.getInstance("AES").generateKey();
        EncryptionStrategy aesStrategy = new AESEncryptionStrategy();
        MessageEncrypter encrypter = new MessageEncrypter(aesStrategy, aesKey);

        String originalMessage = "Hello, World!";
        String encryptedMessage = encrypter.encryptMessage(originalMessage);
        String decryptedMessage = encrypter.decryptMessage(encryptedMessage);

        assertEquals(originalMessage, decryptedMessage);
    }

    @Test
    void testDESEncryption() throws Exception {
        SecretKey desKey = KeyGenerator.getInstance("DES").generateKey();
        EncryptionStrategy desStrategy = new DESEncryptionStrategy();
        MessageEncrypter encrypter = new MessageEncrypter(desStrategy, desKey);

        String originalMessage = "Hello, World!";
        String encryptedMessage = encrypter.encryptMessage(originalMessage);
        String decryptedMessage = encrypter.decryptMessage(encryptedMessage);

        assertEquals(originalMessage, decryptedMessage);
    }
}
