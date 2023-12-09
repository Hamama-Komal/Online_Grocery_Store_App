package com.example.myshop.Models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {

    String name, img_url, description, type, rating;
    int price;

    public ViewAllModel(String name, String img_url, String description, String type, String rating, int price) {
        this.name = name;
        this.img_url = img_url;
        this.description = description;
        this.type = type;
        this.rating = rating;
        this.price = price;
    }

    public ViewAllModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
