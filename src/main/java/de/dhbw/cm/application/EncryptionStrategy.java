package de.dhbw.cm.application;

import javax.crypto.SecretKey;

public interface EncryptionStrategy {
    String encrypt(String plainText, SecretKey secretKey) throws Exception;
    String decrypt(String encryptedText, SecretKey secretKey) throws Exception;
}
