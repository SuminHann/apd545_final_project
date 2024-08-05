package com.example.apd545_final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {
    private Stage primaryStage;
    private List<User> users = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();
    private User user;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        primaryStage.setTitle("APD545 Final Project");
        showAuthView();
    }

    public void showAuthView() {
        try {
            FXMLLoader authView = new FXMLLoader(MainApp.class.getResource("authentication-view.fxml"));
            Scene authScene = new Scene(authView.load());
            AuthController authController = authView.getController();
            authController.setMainApp(this);
            setPrimaryStage(authScene, "Register/Login");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public void showMainView() {
        try {
            FXMLLoader mainView = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
            Scene mainScene = new Scene(mainView.load());
            setPrimaryStage(mainScene, "Travel Diary App");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void setPrimaryStage(Scene scene, String title) {
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}