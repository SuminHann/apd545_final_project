package com.example.apd545_final_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JournalController {
    private List<Journal> journals = new ArrayList<>();

    public void updateJournalEntry(Journal oldEntry, Journal newEntry) {
        if (oldEntry != null) {
            journals.remove(oldEntry);
        }
        journals.add(newEntry);
        saveJournalEntries();
    }

    private void saveJournalEntries() {
        try (FileWriter writer = new FileWriter(new File("journal_entries.txt"))) {
            for (Journal journal : journals) {
                writer.write(journal.getTitle() + journal.getContent() + journal.getImages() + journal.getCreated() + journal.getUpdated() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
