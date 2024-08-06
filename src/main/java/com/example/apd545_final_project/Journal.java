package com.example.apd545_final_project;

import java.util.Objects;

public class Journal {
    private String title;
    private String content;
    private String imagePath;
    private String created;
    private String updated;

    public Journal(String title, String content, String imagePath, String created, String updated) {
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.created = created;
        this.updated = updated;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return title + " | Created: " + created;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Journal journal = (Journal) obj;
        return title.equals(journal.title) && content.equals(journal.content) && imagePath.equals(journal.imagePath) && created.equals(journal.created) && updated.equals(journal.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, imagePath, created, updated);
    }
}
