package com.example.spacetrader.model;

import com.example.spacetrader.model.event.Event;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.model.system.shop.Transaction;
import com.example.spacetrader.model.system.shop.TransactionParty;

/**
 * Class that  holds information about the player character
 * name: the name of the player
 * pilot, fighter, trader, engineer: number of skill points assigned to these skills
 * ship: stores the current spaceship of the player
 * credits: the amount of money the player has
 * SKILL_POINT_MAX: The maximum allocatable points across the four skills
 */
public class Player {
    public static final int SKILL_POINT_MAX = 16;

    public enum Skill {
        PILOT("Pilot"),
        FIGHTER("Fighter"),
        TRADER("Trader"),
        ENGINEER("Engineer");

        private final String stringRep;
        Skill(String stringRep) {
            this.stringRep = stringRep;
        }
    }

    private String name;
    private int pilot;
    private int fighter;
    private int trader;
    private int engineer;
    private Ship ship;
    private int credits;
    private int currentSystemIndex;
    private Event currentEvent;

    /**
     * Initializes a player class, including a Gnat ship and 1000 credits
     * @param name the name of the player
     * @param pilot the number of skill points in pilot
     * @param fighter the number of skill points in fighter
     * @param trader the number of skill points in trader
     * @param engineer the number of skill points in engineer
     */
    public Player(String name, int pilot, int fighter, int trader, int engineer) {
        this.name = name;
        this.pilot = pilot;
        this.fighter = fighter;
        this.trader = trader;
        this.engineer = engineer;
        ship = new Gnat();
        credits = 10000;
        currentSystemIndex = 0;
        getCurrentSystem().generateGoodsAndPrices();
    }

    /**
     * Initializes a player with average stats
     */
    public Player() {
        this("Enter Name", 4, 4, 4, 4);
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getPilot() {return pilot;}
    public void setPilot(int pilot) {this.pilot = pilot;}
    public int getFighter() {return fighter;}
    public void setFighter(int fighter) {this.fighter = fighter;}
    public int getTrader() {return trader;}
    public void setTrader(int trader) {this.trader = trader;}
    public int getEngineer() {return engineer;}
    public void setEngineer(int engineer) {this.engineer = engineer;}
    public Ship getShip() {return ship;}
    public void setShip(Ship ship) {this.ship = ship;}
    public int getCredits() {return credits;}
    public void setCredits(int credits) {this.credits = credits;}
    public SolarSystem getCurrentSystem() {return GameState.getState().getUniverse().getSystems().get(currentSystemIndex);}
    public void setCurrentSystemIndex(int currentSystemIndex) {this.currentSystemIndex = currentSystemIndex;}
    public int getCurrentSystemIndex() {return currentSystemIndex;}
    public Event getCurrentEvent() { return currentEvent;}
    /**
     * Used to calculate the number of skill points the player has
     * @return the number of skill points allocated
     */
    public int skillPointSum() {
        return pilot + fighter + trader + engineer;
    }

    public boolean removeCredits(int quantity) {
        if (credits - quantity >= 0) {
            credits -= quantity;
            return true;
        }
        return false;
    }

    public void addCredits(int quantity) {
        credits += quantity;
    }

    public void travelToSystem(int newSystemIndex) {
        SolarSystem oldSystem = getCurrentSystem();
        SolarSystem newSystem = GameState.getState().getUniverse().getSystems().get(newSystemIndex);

        if (oldSystem != null) {
            oldSystem.setVisited(true);
            getShip().subtractFuel(oldSystem.getPosition().getEuclideanDistanceTo(newSystem.getPosition()));
        }

        this.currentSystemIndex = newSystemIndex;

        newSystem.generateGoodsAndPrices();
    }

    public Transaction.Result performTransaction(Transaction transaction, TransactionParty otherParty) {
        switch (transaction.type) {
            case BUY:
                int price = otherParty.getBuyPrice(transaction.good);
                // Check if we have enough money.
                if (getCredits() < price) {
                    return Transaction.Result.NOT_ENOUGH_MONEY;
                }
                // Check if we have enough inventory space.
                if (getShip().getNumOpenCargoBays() < transaction.quantity) {
                    return Transaction.Result.INVENTORY_FULL;
                }
                // Check if the system actually has the item.
                if (otherParty.getInventoryQuantity(transaction.good) < transaction.quantity) {
                    return Transaction.Result.NO_GOODS_LEFT;
                }
                // Now we can complete the transaction.
                otherParty.changeInventoryQuantityByAmount(transaction.good, -transaction.quantity);
                getShip().addToInventory(transaction.good, transaction.quantity);
                removeCredits(price);
                break;
            case SELL:
                int sellPrice = otherParty.getSellPrice(transaction.good);
                // Check that we have enough of the item
                if (getShip().getQuantityOfTradeGood(transaction.good) < transaction.quantity) {
                    return Transaction.Result.NO_GOODS_LEFT;
                }
                // Complete the transaction.
                otherParty.changeInventoryQuantityByAmount(transaction.good, transaction.quantity);
                getShip().removeFromInventory(transaction.good, transaction.quantity);
                addCredits(sellPrice);
                break;
        }
        return Transaction.Result.SUCCESS;
    }
}
