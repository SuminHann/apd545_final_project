package com.example.apd545_final_project;

import java.util.List;

public class User {
    String username;
    String name;
    List<Journal> journals;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public User(String username, String name, List<Journal> journals) {
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

    public List<Journal> getJournals() {
        return journals;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
