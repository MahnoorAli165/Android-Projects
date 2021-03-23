package com.example.daraz;

import java.io.Serializable;

public class Product implements Serializable {

    private int ID;
    private String name;
    private int stock;
    private String description;
    private int price;
    private String imageLink;

    public Product(int ID, String name, int stock, String description, int price, String imageLink) {
        this.ID = ID;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
    }

    public Product(String data) {
        String data_[] = data.split("[|]");
        this.ID = Integer.parseInt(data_[0]);
        this.name = data_[1];
        this.stock = Integer.parseInt(data_[2]);
        this.description = data_[3];
        this.price = Integer.parseInt(data_[4]);
        this.imageLink = data_[5];
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
