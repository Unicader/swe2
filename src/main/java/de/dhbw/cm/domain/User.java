package de.dhbw.cm.domain;

import de.dhbw.cm.application.Date;
import de.dhbw.cm.application.Hash;

public class User {
    private final String username;
    private final String hashedPassword;

    public User(String username, String password) {
        this.username = Hash.hashFunction(username);
        this.hashedPassword = Hash.hashFunction(password);
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean checkPassword(String password) {
        return this.hashedPassword.equals(Hash.hashFunction(password));
    }
}