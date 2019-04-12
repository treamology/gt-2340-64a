package com.example.spacetrader.model;

import java.util.HashMap;

/**
 * Class that holds a spaceship. The different ship types are subclasses
 */
public abstract class Ship {

    private final String type;
    private HashMap<TradeGood, Integer> inventory;
    private final int totalCargoBays;
    private final int maxFuel; // 1 fuel = 1 unit
    private int currentFuel;

    Ship(String type, int totalCargoBays, int maxFuel) {
        this.inventory = new HashMap<>();
        for (TradeGood good : TradeGood.values()) {
            inventory.put(good, 0);
        }

        this.type = type;
        this.totalCargoBays = totalCargoBays;
        this.maxFuel = maxFuel;
        this.currentFuel = maxFuel;
    }

    @Override
    public String toString() {
        return type;
    }
    /**
     * Returns the number of cargo bays
     * @return the number of cargo bays
     */
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
        if (inventory.get(good) == null) {
            inventory.put(good, amount);
        } else {
            inventory.put(good, inventory.get(good) + amount);
        }
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
        if (inventory.get(good) == null) {
            return 0;
        } else {
            return inventory.get(good);
        }
    }

    /**
     * This is a package-private method that is meant for moving inventory from one ship to another
     * when purchasing a new ship.
     * @param inventory The old ship's inventory.
     */
    public void setInventory(HashMap<TradeGood, Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * If you resist pirates and fail, you lose all trade goods
     */
    public void loseInventory() {
        inventory.clear();
    }
    /**
     * Used to see what is in the inventory
     * @return HashMap<TradeGood, Integer> a hashmap of the player's inventory
     */
    public HashMap<TradeGood, Integer> getInventory() {return inventory;}
    /**
     * Check how much fuel a player has
     * @return the amount of fuel a player has
     */
    public int getCurrentFuel() {
        return currentFuel;
    }
    /**
     * Check how much fuel a player can have
     * @return the max amount of fuel a player can have
     */
    public int getMaxFuel() {
        return maxFuel;
    }
    /**
     * Subtracts fuel from the player, used for traveling
     * @param amount the amount of fuel that is subtracted
     */
    public void subtractFuel(int amount) {
        currentFuel -= amount;
    }
    /**
     * Adds fuel to the player. Used when purchasing fuel
     * @param amount the amount of fuel to add
     */
    public void addFuel(int amount) {
        currentFuel = Math.min(currentFuel + amount, maxFuel);
    }
    /**
     * Set's the player's current fuel to a specified value
     * @param currentFuel the value to set the player's fuel to
     */
    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }
}
