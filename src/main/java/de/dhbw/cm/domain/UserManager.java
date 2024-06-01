package de.dhbw.cm.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.dhbw.cm.application.Hash;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final String STANDARD_FILE_PATH = "src/main/java/resources/users.json";
    private final String FILE_PATH;
    private Map<String, User> users = new HashMap<>();

    public UserManager() {
        this.FILE_PATH = STANDARD_FILE_PATH;
        loadUsers();
    }

    public UserManager(String filePath) {
        this.FILE_PATH = filePath;
        loadUsers();
    }

    public boolean createUser(String username, String password) {
        User user = new User(username, password);
        if (users.containsKey(user.getUsername())) {
            return false; // User already exists
        }
        users.put(user.getUsername(), new User(username, password));
        saveUsers();
        return true;
    }

    public User loginUser(String username, String password) {
        User user = users.get(Hash.hashFunction(username));
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    void loadUsers() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Map<String, User>>() {
            }.getType();
            users = new Gson().fromJson(reader, type);
        } catch (IOException e) {
            // File not found, starting with an empty user list
            users = new HashMap<>();
        }
    }

    void saveUsers() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getUsers() {
        loadUsers();
        return users.size();
    }
}
