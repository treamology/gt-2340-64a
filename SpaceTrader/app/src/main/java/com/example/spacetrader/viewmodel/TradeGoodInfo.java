package com.example.spacetrader.viewmodel;

import com.example.spacetrader.model.TradeGood;
/**
 * Trade good info class
 */
public class TradeGoodInfo {
    private final String name;
    private final int price;
    int quantity;
    int shipQuantity;
    private final TradeGood good;
    /**
     * Initializes the trade good
     * @param name the name
     * @param good the good
     * @param price the price
     * @param quantity the quantity of the good
     * @param shipQuantity how much is in the player's ship
     */
    public TradeGoodInfo(String name, int price, int quantity, int shipQuantity, TradeGood good) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shipQuantity = shipQuantity;
        this.good = good;
    }
    /**
     * Gets the Trade good name
     * @return the Trade good name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Trade good price
     * @return the Trade good price
     */

    public int getPrice() {
        return price;
    }

    /**
     * Gets the Trade good quantity
     * @return the Trade good quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the Trade good quantity in the player's ship
     * @return the Trade good quantity in the player's ship
     */
    public int getShipQuantity() {
        return shipQuantity;
    }

    /**
     * Gets the Trade good quantity
     * @param quantity the Trade good quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the Trade good quantity in the player's ship
     * @param shipQuantity the quantity to set the shipQuantity to
     */
    public void setShipQuantity(int shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    /**
     * Gets the Trade good
     * @return the Trade good
     */
    TradeGood getGood() {
        return good;
    }
}
