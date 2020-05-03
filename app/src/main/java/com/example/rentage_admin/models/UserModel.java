package com.example.rentage_admin.models;

public class UserModel {
    String id;
    String name;
    String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;

    public UserModel(String id, String name, String imageUrl, String email) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.email = email;
    }


}
