package com.example.apd545_final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        loadUserJournals();
    }

    public void loadUserJournals() {
        User user = mainApp.getUser();
        if (user == null) return;

        String fileName = user.getUsername() + "_journals.txt";
        File userFile = new File(fileName);

        if (!userFile.exists()) {
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Journal> journals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length >= 5) {
                    Journal journal = new Journal(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    journals.add(journal);
                }
            }
            ObservableList<Journal> observableJournals = FXCollections.observableArrayList(journals);
            user.setJournals(observableJournals);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveJournal(Journal journal) {
        User user = mainApp.getUser();
        if (user == null) return;

        String fileName = user.getUsername() + "_journals.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(journal.getTitle() + "," + journal.getContent() + "," + journal.getImagePath() + "," + journal.getCreated() + "," + journal.getUpdated() + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the journal: " + e.getMessage());
        }
    }

    public void updateJournal(Journal oldJournal, Journal newJournal) {
        if (oldJournal != null) {
            String fileName = mainApp.getUser().getUsername() + "_journals.txt";
            List<Journal> journals = new ArrayList<>();

            // Read all journals and update the old one
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 5);
                    if (parts.length >= 5) {
                        Journal journal = new Journal(parts[0], parts[1], parts[2], parts[3], parts[4]);
                        if (!journal.equals(oldJournal)) {
                            journals.add(journal);
                        }
                    }
                }
                journals.add(newJournal);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (Journal journal : journals) {
                        writer.write(String.join(",", journal.getTitle(), journal.getContent(), journal.getImagePath(), journal.getCreated(), journal.getUpdated()) + "\n");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while updating the journal: " + e.getMessage());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteJournal(Journal journalToDelete) {
        if (journalToDelete != null) {
            String fileName = mainApp.getUser().getUsername() + "_journals.txt";
            List<Journal> journals = new ArrayList<>();

            // Read all journals and exclude the one to be deleted
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 5);
                    if (parts.length >= 5) {
                        Journal journal = new Journal(parts[0], parts[1], parts[2], parts[3], parts[4]);
                        if (!journal.equals(journalToDelete)) {
                            journals.add(journal);
                        }
                    }
                }

                // Save the remaining journals back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (Journal journal : journals) {
                        writer.write(String.join(",", journal.getTitle(), journal.getContent(), journal.getImagePath(), journal.getCreated(), journal.getUpdated()) + "\n");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while deleting the journal: " + e.getMessage());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
