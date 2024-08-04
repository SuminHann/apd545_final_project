package com.example.apd545_final_project;

import java.util.List;

public class User {
    String username;
    String name;
    String password;
    List<Journal> journals;

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
