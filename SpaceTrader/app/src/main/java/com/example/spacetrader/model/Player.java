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
    /**
     * Gets the player's name
     * @return the player's name
     */
    public String getName() {return name;}

    /**
     * Sets the player's name
     * @param name the player's name to set
     */
    public void setName(String name) {this.name = name;}

    /**
     * Gets the player's pilot skill
     * @return the player's pilot skill
     */
    public int getPilot() {return pilot;}

    /**
     * Sets the player's pilot skill
     * @param pilot the player's pilot skill to set
     */
    public void setPilot(int pilot) {this.pilot = pilot;}

    /**
     * Gets the player's fighter skill
     * @return the player's fighter skill
     */
    public int getFighter() {return fighter;}

    /**
     * Sets the player's fighter skill
     * @param fighter the player's fighter skill to set
     */
    public void setFighter(int fighter) {this.fighter = fighter;}

    /**
     * Gets the player's trader skill
     * @return the player's trader skill
     */
    public int getTrader() {return trader;}

    /**
     * Sets the player's trader skill
     * @param trader the player's trader skill to set
     */
    public void setTrader(int trader) {this.trader = trader;}

    /**
     * Gets the player's engineer skill
     * @return the player's engineer skill
     */
    public int getEngineer() {return engineer;}

    /**
     * Sets the player's engineer skill
     * @param engineer the player's engineer skill to set
     */
    public void setEngineer(int engineer) {this.engineer = engineer;}
    /**
     * Gets the player's ship
     * @return the player's ship
     */
    public Ship getShip() {return ship;}

    /**
     * Sets the player's ship
     * @param ship the player's ship to set
     */
    public void setShip(Ship ship) {this.ship = ship;}

    /**
     * Gets the player's credits
     * @return the player's credits
     */
    public int getCredits() {return credits;}

    /**
     * Sets the player's credits
     * @param credits the player's credits to set
     */
    public void setCredits(int credits) {this.credits = credits;}

    /**
     * Gets the current system
     * @return the current system
     */
    public SolarSystem getCurrentSystem() {return GameState.getState().getUniverse().getSystems().get(currentSystemIndex);}

    /**
     * Sets the current system
     * @param currentSystemIndex what to set the current system to
     */
    public void setCurrentSystemIndex(int currentSystemIndex) {this.currentSystemIndex = currentSystemIndex;}

    /**
     * Gets the current system index
     * @return the current system index
     */
    public int getCurrentSystemIndex() {return currentSystemIndex;}

    /**
     * Gets the current Event
     * @return the current event
     */
    public Event getCurrentEvent() { return currentEvent;}
    /**
     * Used to calculate the number of skill points the player has
     * @return the number of skill points allocated
     */
    public int skillPointSum() {
        return pilot + fighter + trader + engineer;
    }
    /**
     * Removes a specified number of credits from the player
     * @param quantity the amount of credits to remove
     * @return whether or not the credits where removed
     */
    public boolean removeCredits(int quantity) {
        if (credits - quantity >= 0) {
            credits -= quantity;
            return true;
        }
        return false;
    }
    /**
     * Adds a specified number of credits to the player
     * @param quantity the number of credits to add
     */
    public void addCredits(int quantity) {
        credits += quantity;
    }
    /**
     * Player travels to system
     * @param newSystemIndex the index of the system the player is traveling to
     */
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
    /**
     * Player attempts to make a monetary transaction with another entity
     * @param transaction the transaction that is being made
     * @param otherParty the other party that is participating in the transaction
     * @return Transaction.Result the result of the transaction
     */
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
