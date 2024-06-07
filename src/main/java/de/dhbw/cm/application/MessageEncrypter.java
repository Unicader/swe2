package de.dhbw.cm.application;

import javax.crypto.SecretKey;

public class MessageEncrypter {
    private final EncryptionStrategy encryptionStrategy;
    private final SecretKey secretKey;

    public MessageEncrypter(EncryptionStrategy encryptionStrategy, SecretKey secretKey) {
        this.encryptionStrategy = encryptionStrategy;
        this.secretKey = secretKey;
    }

    public String encryptMessage(String plainText) throws Exception {
        return encryptionStrategy.encrypt(plainText, secretKey);
    }

    public String decryptMessage(String encryptedText) throws Exception {
        return encryptionStrategy.decrypt(encryptedText, secretKey);
    }
}
