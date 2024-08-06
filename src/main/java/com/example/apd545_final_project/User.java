package com.example.apd545_final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    String username;
    String name;
    private ObservableList<Journal> journals = FXCollections.observableArrayList();

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public User(String username, String name, ObservableList<Journal> journals) {
        this.username = username;
        this.name = name;
        this.journals = journals;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public ObservableList<Journal> getJournals() {
        return journals;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJournals(ObservableList<Journal> journals) {
        this.journals = journals;
    }
}
