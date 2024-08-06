package com.example.apd545_final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AuthController {

    private Map<String, String> userCredentials = new HashMap<>();
    private MainApp mainApp;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private Button registerToggleBtn;

    public AuthController() {
        File credentialsFile = new File("user_credentials.txt");
        if (!credentialsFile.exists()) {
            try {
                credentialsFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Load existing user credentials from file
        try {
            Files.lines(Paths.get("user_credentials.txt")).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 2) { // Check to avoid ArrayIndexOutOfBoundsException
                    userCredentials.put(parts[0], parts[1]);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean register(String username, String password) {
        if (userCredentials.containsKey(username)) {
            return false; // Username already exists
        }
        userCredentials.put(username, password);
        saveCredentials();
        return true;
    }

    public boolean login(String username, String password) {
        if (userCredentials.getOrDefault(username, "").equals(password)) {
            User loginUser = new User(username, password);
            mainApp.setUser(loginUser);
            return true;
        }
        return false;
    }

    private void saveCredentials() {
        try (FileWriter writer = new FileWriter(new File("user_credentials.txt"))) {
            for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void loginUser() {
        String username = this.username.getText();
        String password = this.password1.getText();
        if (login(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            mainApp.showMainView(); // Switch to main view
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    @FXML
    void registerUser(ActionEvent event) {
        String username = this.username.getText();
        String name = this.name.getText();
        String password = this.password1.getText();
        String confirmPassword = this.password2.getText();

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Passwords do not match.");
            return;
        }
        if (register(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Account created for " + username + "!");
            User newUser = new User(username, name);
            mainApp.getUsers().add(newUser);
            toggleRegister();
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Username already exists.");
        }
    }

    @FXML
    void toggleRegister() {
        name.setVisible(!name.isVisible());
        name.setManaged(!name.isManaged());
        password2.setVisible(!password2.isVisible());
        password2.setManaged(!password2.isManaged());
        loginBtn.setVisible(!loginBtn.isVisible());
        loginBtn.setManaged(!loginBtn.isManaged());
        registerBtn.setVisible(!registerBtn.isVisible());
        registerBtn.setManaged(!registerBtn.isManaged());

        username.setText("");
        name.setText("");
        password1.setText("");
        password2.setText("");

        registerToggleBtn.setText(registerToggleBtn.getText().equals("Register") ? "Login" : "Register");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
