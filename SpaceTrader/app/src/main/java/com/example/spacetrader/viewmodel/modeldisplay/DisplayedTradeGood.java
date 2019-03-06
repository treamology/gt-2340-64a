package com.example.spacetrader.viewmodel.modeldisplay;

public class DisplayedTradeGood {
    String name;
    int price;
    int quantity;
    int shipQuantity;

    public DisplayedTradeGood(String name, int price, int quantity, int shipQuantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shipQuantity = shipQuantity;

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

    public int getShipQuantity() {
        return shipQuantity;
    }
}
