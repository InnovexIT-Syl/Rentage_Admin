package com.example.rentage_admin.models;

import java.io.Serializable;

public class CarServiceModel implements Serializable {
    String id;
    String description;
    String title;
    String imageUrl;
    String price;


    public CarServiceModel() {
    }

    public CarServiceModel(String id, String description, String title, String imageUrl, String price) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

