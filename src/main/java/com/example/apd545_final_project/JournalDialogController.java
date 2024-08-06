package com.example.apd545_final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalDialogController {

    @FXML
    private Button addBtn;

    @FXML
    private TextArea content;

    @FXML
    private TextField title;

    @FXML
    private Label imagePath;

    @FXML
    private Button uploadBtn;

    @FXML
    private ImageView imageView;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Journal journalToEdit;

    public void setJournal(Journal journal) {
        this.journalToEdit = journal;
        title.setText(journal.getTitle());
        content.setText(journal.getContent());
        imagePath.setText(journal.getImagePath());
        Image image = new Image(new File(journal.getImagePath()).toURI().toString());
        imageView.setImage(image);
    }

    @FXML
    void handleAddJournal(ActionEvent event) {
        if (mainApp != null) {
            User currentUser = mainApp.getUser();
            if (currentUser != null) {
                String title = this.title.getText();
                String content = this.content.getText();
                String date = getCurrentDate();
                String imagePath = this.imagePath.getText();
                Journal updatedJournal = new Journal(title, content, imagePath, date, date);

                if (journalToEdit != null) {
                    // Update the existing journal
                    updatedJournal.setCreated(journalToEdit.getCreated());
                    UserController userController = new UserController();
                    userController.setMainApp(mainApp);
                    userController.updateJournal(journalToEdit, updatedJournal);

                    ObservableList<Journal> observableJournals = FXCollections.observableArrayList(currentUser.getJournals());
                    observableJournals.remove(journalToEdit);
                    observableJournals.add(updatedJournal);
                    currentUser.setJournals(observableJournals);
                } else {
                    // Create a new journal entry
                    UserController userController = new UserController();
                    userController.setMainApp(mainApp);
                    userController.saveJournal(updatedJournal);

                    ObservableList<Journal> observableJournals = FXCollections.observableArrayList(currentUser.getJournals());
                    observableJournals.add(updatedJournal);
                    currentUser.setJournals(observableJournals);
                }

                Stage stage = (Stage) addBtn.getScene().getWindow();
                stage.close();
            } else {
                System.out.println("MainApp is not set.");
            }
        }
    }


    @FXML
    void handleUploadImages(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(uploadBtn.getScene().getWindow());
        if (selectedFile != null) {
            imagePath.setText(selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    private String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }
}
