package com.example.apd545_final_project;

import javafx.scene.image.Image;

import java.util.Date;
import java.util.List;

public class Journal {
    String title;
    String body;
    List<Image> images;
    Date created;
    Date updated;

    public Journal(String title, String body, List<Image> images, Date created, Date updated) {
        this.title = title;
        this.body = body;
        this.images = images;
        this.created = created;
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public List<Image> getImages() {
        return images;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
