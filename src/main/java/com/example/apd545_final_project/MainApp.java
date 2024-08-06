package com.example.apd545_final_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private ObservableList<User> users = FXCollections.observableArrayList();
    private ObservableList<Journal> journals = FXCollections.observableArrayList();
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
            MainController mainController = mainView.getController();
            mainController.setMainApp(this);

            // Ensure journals are loaded before showing the main view
            UserController userController = new UserController();
            userController.setMainApp(this);
            userController.loadUserJournals();

            mainController.loadJournals(); // Load journals for the main view
            setPrimaryStage(mainScene, "Travel Diary App");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }


    public ObservableList<User> getUsers() {
        return users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ObservableList<Journal> getJournals() {
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
