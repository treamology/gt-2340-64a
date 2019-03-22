package com.example.spacetrader.model;

import com.example.spacetrader.model.system.SolarSystem;

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

        private String stringRep;
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
    public int getCurrentSystemIndex() {return currentSystemIndex;}
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

    public void setCurrentSystemIndex(int currentSystemIndex) {
        this.currentSystemIndex = currentSystemIndex;
    }
}
