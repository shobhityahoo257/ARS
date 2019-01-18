 package com.example.shobhitverma.ars.Home;


public class Product {
    private int id;
    private String title;
    private String Description;
    private double rating;
    private double price;
    private int img;

    public Product(int id, String title, String description, double rating, double price, int img) {
        this.id = id;
        this.title = title;
        Description = description;
        this.rating = rating;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImg() {
        return img;
    }
}