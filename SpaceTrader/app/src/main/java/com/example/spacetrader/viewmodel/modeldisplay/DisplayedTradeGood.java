package com.example.spacetrader.viewmodel.modeldisplay;

public class DisplayedTradeGood {
    String name;
    int price;
    int quantity;

    public DisplayedTradeGood(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
