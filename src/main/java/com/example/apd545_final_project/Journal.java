package com.example.apd545_final_project;

public class Journal {
    String title;
    String content;
    String imagePath;
    String created;
    String updated;

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

    public String getImages() {
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

    public void setBody(String content) {
        this.content = content;
    }

    public void setImages(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
