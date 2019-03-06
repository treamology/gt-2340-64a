package com.example.spacetrader.model;

import java.util.HashMap;
import java.util.List;

/**
 * Class that holds a spaceship. The different ship types are subclasses
 */
public abstract class Ship {

    String type;
    HashMap<TradeGood, Integer> inventory;
    int totalCargoBays;

    public Ship () {
        this.inventory = new HashMap<>();
        for (TradeGood good : TradeGood.values()) {
            inventory.put(good, 0);
        }
    }

    @Override
    public String toString() {
        return type;
    }

    public int getTotalCargoBays() {
        return totalCargoBays;
    }

    /**
     * Counts the number of items currently in the inventory and returns the number of slots
     * remaining.
     * @return Number of open inventory slots remaining.
     */
    public int getNumOpenCargoBays() {
        int numInventoryItems = 0;
        for (TradeGood good : inventory.keySet()) {
            numInventoryItems += inventory.get(good);
        }
        return totalCargoBays - numInventoryItems;
    }

    /**
     * Adds a certain amount of an item to the ship's cargo bay.
     * @param good The type of good.
     * @param amount The amount of the good to add to the existing amount.
     * @return true if update succeeded, false if not enough inventory space.
     */
    public boolean addToInventory(TradeGood good, int amount) {
        if (getNumOpenCargoBays() - amount < 0) {
            return false;
        }
        inventory.put(good, inventory.get(good) + amount);
        return true;
    }

    /**
     * Removes a certain amount of an item to the ship's cargo bay.
     * @param good The type of good.
     * @param amount The amount of the good to remove from the existing amount.
     * @return true if update succeeded, false if too many of the item is being removed.
     */
    public boolean removeFromInventory(TradeGood good, int amount) {
        if (getQuantityOfTradeGood(good) - amount < 0) {
            return false;
        }
        inventory.put(good, getQuantityOfTradeGood(good) - amount);
        return true;
    }

    /**
     * Returns the quantity of a particular trade good.
     * @param good The trade good in our inventory
     * @return The number of goods in our cargo bay.
     */
    public int getQuantityOfTradeGood(TradeGood good) {
        return inventory.get(good);
    }

    /**
     * This is a package-private method that is meant for moving inventory from one ship to another
     * when purchasing a new ship.
     * @param inventory The old ship's inventory.
     */
    protected void setInventory(HashMap<TradeGood, Integer> inventory) {
        this.inventory = inventory;
    }
}
