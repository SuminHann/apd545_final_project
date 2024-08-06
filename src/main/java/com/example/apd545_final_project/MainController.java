package com.example.apd545_final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

// Other imports...

public class MainController {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label journal;

    @FXML
    private ListView<Journal> journalsList;

    @FXML
    private Button editBtn;  // Add this button to your FXML file


    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        ObservableList<Journal> observableJournals = FXCollections.observableArrayList(mainApp.getUser().getJournals());
        journalsList.setItems(observableJournals);
        loadJournals(); // Load journals when setting the main app

    }

    @FXML
    void addJournal(ActionEvent event) {
        try {
            FXMLLoader journalDialogLoader = new FXMLLoader(MainController.class.getResource("journalDialog-view.fxml"));
            Scene scene = new Scene(journalDialogLoader.load());
            JournalDialogController controller = journalDialogLoader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Journal");
            dialogStage.setScene(scene);

            controller.setMainApp(mainApp);

            dialogStage.showAndWait();

            // Refresh the journals list
            ObservableList<Journal> observableJournals = FXCollections.observableArrayList(mainApp.getUser().getJournals());
            journalsList.setItems(observableJournals);
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    // Load journals for the ListView
    public void loadJournals() {
        if (mainApp != null && mainApp.getUser() != null) {
            ObservableList<Journal> journals = mainApp.getUser().getJournals();
            if (journals != null) {
                journalsList.setItems(journals);
                System.out.println("Journals displayed: " + journals.size()); // Debugging line
            }
        }
    }

    @FXML
    void editJournal(ActionEvent event) {
        Journal selectedJournal = journalsList.getSelectionModel().getSelectedItem();
        if (selectedJournal != null) {
            try {
                FXMLLoader loader = new FXMLLoader(MainController.class.getResource("journalDialog-view.fxml"));
                Scene scene = new Scene(loader.load());
                JournalDialogController controller = loader.getController();
                controller.setMainApp(mainApp);

                // Set the selected journal data into the dialog for editing
                controller.setJournal(selectedJournal);

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Journal");
                dialogStage.setScene(scene);
                dialogStage.showAndWait();

                // Refresh the journals list
                ObservableList<Journal> observableJournals = FXCollections.observableArrayList(mainApp.getUser().getJournals());
                journalsList.setItems(observableJournals);
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a journal entry to edit.");
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Label getJournal() {
        return journal;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public ListView<Journal> getJournalsList() {
        return journalsList;
    }
}
