package com.example.rentage_admin.models;

public class CategoryModel {
    String id;
    String description;
    String title;
    String imageUrl;


    public CategoryModel() {
    }
    public CategoryModel(String id, String description, String title, String imageUrl) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public CategoryModel(String id, String description, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
