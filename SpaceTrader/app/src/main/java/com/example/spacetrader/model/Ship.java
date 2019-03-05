package com.example.spacetrader.model;

import java.util.LinkedList;

/**
 * Class that holds a spaceship. The different ship types are subclasses
 */
public abstract class Ship {

    String type;
    LinkedList<TradeGood> cargoBay;
    int cargoSlots;

    public Ship () {
        cargoBay = new LinkedList<>();
    }

    @Override
    public String toString() {
        return type;
    }

    /**
     * Puts a bought trade good into the cargo bay
     * @param t the trade good to add
     */
    public void addCargo(TradeGood t) {
        if (cargoBay.size() < cargoSlots) {
            cargoBay.addLast(t);
        } else {
            //Throw new exception or something
        }
    }

    /**
     * @return the number of free slots in the cargo bay
     */
    public int getInventorySpace() {
        return cargoSlots - cargoBay.size();
    }

    /**
     * Attempts to remove a specific type of cargo from the ship
     * @param t the type of cargo to remove
     * @return the removed type of cargo, or null if it isn't present
     */
    public TradeGood sellCargo(TradeGood t) {
        if (cargoBay.removeFirstOccurrence(t)) {
            return t;
        } else {
            return null;
        }
    }

    public LinkedList getStorage() {
        return cargoBay;
    }
}
