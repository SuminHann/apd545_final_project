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

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return title + " | Created: " + created; // Customize this format as needed
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Journal journal = (Journal) obj;
        return title.equals(journal.title) &&
                content.equals(journal.content) &&
                imagePath.equals(journal.imagePath) &&
                created.equals(journal.created) &&
                updated.equals(journal.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, imagePath, created, updated);
    }

}
