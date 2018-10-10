package com.example.swarangigaurkar.learnersapp;

import android.graphics.drawable.Drawable;

public class Product {

    private int id;
    private String title;
    private  double rating;
    private  String price;
    private  int image;


    public Product(int id, String title, double rating, String price, int image) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
