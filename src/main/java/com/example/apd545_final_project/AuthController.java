package com.example.apd545_final_project;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class AuthController {

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

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void loginUser(ActionEvent event) {
        String user = username.getText();
        String pass = password1.getText();

        List<User> users = mainApp.getUsers();
        Optional<User> loginUser = users.stream().filter(u -> u.getUsername().equals(user) && u.getPassword().equals(pass)).findFirst();

        if(loginUser.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Username or password is incorrect.");
            alert.showAndWait();
            return;
        }

        mainApp.showMainView();

    }

    @FXML
    void registerUser(ActionEvent event) {
        String user = username.getText();
        String nameText = name.getText();
        String pass1 = password1.getText();
        String pass2 = password2.getText();

        List<User> users = mainApp.getUsers();

        if (!pass1.equals(pass2)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Passwords do not match.");
            alert.showAndWait();
            return;
        }

        Optional<User> existingUser = users.stream().filter(u -> u.getUsername().equals(user)).findFirst();

        if(existingUser.isPresent()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Username already exists.");
            alert.showAndWait();
        } else {
            User newUser = new User(user, nameText, pass1);
            mainApp.getUsers().add(newUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration successful.");
            alert.showAndWait();
            toggleRegister();
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

}