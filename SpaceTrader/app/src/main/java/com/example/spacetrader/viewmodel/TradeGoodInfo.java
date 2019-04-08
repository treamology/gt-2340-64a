package com.example.spacetrader.viewmodel;

import com.example.spacetrader.model.TradeGood;

public class TradeGoodInfo {
    private final String name;
    private final int price;
    int quantity;
    int shipQuantity;
    private final TradeGood good;

    public TradeGoodInfo(String name, int price, int quantity, int shipQuantity, TradeGood good) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shipQuantity = shipQuantity;
        this.good = good;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShipQuantity(int shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    TradeGood getGood() {
        return good;
    }
}
