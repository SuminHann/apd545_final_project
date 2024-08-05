package com.example.apd545_final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label journal;

    @FXML
    private ListView<Journal> journalsList;

    private MainApp mainApp;
    private List<User> users = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void addJournal(ActionEvent event) {
        try {
            FXMLLoader journalDialog = new FXMLLoader(MainController.class.getResource("journalDialog-view.fxml"));
            Scene scene = new Scene(journalDialog.load());
            JournalDialogController controller = journalDialog.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Journal");
            dialogStage.setScene(scene);
            dialogStage.showAndWait();

//            Journal newJournal = controller.getJournal();
//            if(journal.isPresent()) {
//                mainApp.getJournals().add(journal);
//            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public Label getJournals() {
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

    public void setJournal(Label journal) {
        this.journal = journal;
    }

    public void setAddBtn(Button addBtn) {
        this.addBtn = addBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public void setJournalsList(ListView<Journal> journalsList) {
        this.journalsList = journalsList;
    }
}
