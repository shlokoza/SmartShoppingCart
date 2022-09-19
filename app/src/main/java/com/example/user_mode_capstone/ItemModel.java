package com.example.user_mode_capstone;

public class ItemModel {

    private String itemName, location;
    private int quantity;
    private double price, weight;



    public ItemModel(String itemName, int quantity, double price, double weight, String location) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
        this.location = location;
    }
    public String getName() {
        return itemName;
    }
    public String getLocation() {
        return location;
    }
    public double getWeight() {
        return weight;
    }
    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
