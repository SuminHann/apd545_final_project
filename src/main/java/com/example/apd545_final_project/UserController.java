package com.example.apd545_final_project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private MainApp mainApp;

    private UserController() {
        File userFile = new File("user_journal_entries.txt");
        if (!userFile.exists()) {
            try {
                userFile.createNewFile();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        loadUser();
    }

    public void loadUser() {
        try {
            Files.lines(Paths.get("user_journal_entries.txt")).forEach(line -> {
                String[] parts = line.split(",");
                if(parts[0].equals(mainApp.getUser().username)) {
                    String[] journalLines = parts[2].split(";");
                    List<Journal> journals = new ArrayList<>();
                    for (String journal : journalLines) {
                        String[] journalParts = journal.split(",");
                        Journal j = new Journal(journalParts[0], journalParts[1], journalParts[2], journalParts[3], journalParts[4]);
                        journals.add(j);
                        User user = new User(parts[0], parts[1], journals);
                        mainApp.setUser(user);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


}
