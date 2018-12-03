package com.example.mylenovo.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {

    // Class to save a Menu-Item

    private String name;
    private String description;
    private String imageUrl;
    private int price;
    private String category;

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
