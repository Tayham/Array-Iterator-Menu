package com.COSC436;

public class MenuItem {

    String name;
    int category;
    boolean heartHealthy;
    double price;

    public MenuItem(String name, int category, boolean heartHealthy, double price) {
        this.name = name;
        this.category = category;
        this.heartHealthy = heartHealthy;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHeartHealthy() {
        return heartHealthy;
    }

    public String toString() {
        return (name + ", $" + price + "\n   " + category);
    }
}
