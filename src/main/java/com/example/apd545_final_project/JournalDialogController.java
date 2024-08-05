package com.example.apd545_final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalDialogController {

    private MainController mainController;
    private MainApp mainApp;

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
    private String oldEntry;

    public TextField getTitle() {
        return title;
    }

    public TextArea getContent() {
        return content;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public Label getImagePath() {
        return imagePath;
    }

    public void setImagePath(Label imagePath) {
        this.imagePath = imagePath;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void handleAddJournal(ActionEvent event) {
        String title = this.title.getText();
        String content = this.content.getText();
        String date = getCurrentDate();
        String imagePath = this.imagePath.getText();
        Journal newJournal = new Journal(title, content, imagePath, date, date);
        List<Journal> userJournals = mainApp.getUser().getJournals();
        userJournals.add(newJournal);
        mainApp.getUser().setJournals(userJournals);
        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    void handleUploadImages(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(uploadBtn.getScene().getWindow());
        if (selectedFile != null) {
            imagePath.setText(selectedFile.getAbsolutePath());
        }
    }

    public void setJournalEntry(String entry, MainController mainController) {
        this.mainController = mainController;
        this.oldEntry = entry;
        if (entry != null) {
            String[] parts = entry.split(": ");
            String[] titleDate = parts[0].split(" - ");
            title.setText(titleDate[0]);;
            content.setText(parts[1]);
            // Assuming the image path is appended as [Image: <path>]
            if (parts.length > 2) {
                imagePath.setText(parts[2].replace("[Image: ", "").replace("]", ""));
            }
        }
    }

    private String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }

}
