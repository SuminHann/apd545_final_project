package com.example.apd545_final_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MainController {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label journals;

    @FXML
    private ListView<Journal> journalsList;

    @FXML
    void addJournal(ActionEvent event) {
        FXMLLoader journalDialog
    }

    public Label getJournals() {
        return journals;
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

    public void setJournals(Label journals) {
        this.journals = journals;
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
