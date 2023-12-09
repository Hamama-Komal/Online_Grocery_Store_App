package com.example.myshop.Models;

public class RecommendedModel {

    String name, img_url, price, rating, description;

    public RecommendedModel(String name, String img_url, String price, String rating, String description) {
        this.name = name;
        this.img_url = img_url;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    public RecommendedModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
