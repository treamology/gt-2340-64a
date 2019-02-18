package com.example.spacetrader.model;

/**
 * Class that  holds information about the player character
 * name: the name of the player
 * pilot, fighter, trader, engineer: number of skill points assigned to these skills
 * ship: stores the current spaceship of the player
 * credits: the amount of money the player has
 * skillPointMax: The maximum allocatable points across the four skills
 */
public class Player {
    String name;
    int pilot;
    int fighter;
    int trader;
    int engineer;
    Ship ship;
    int credits;
    static final int skillPointMax = 16;

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
        credits = 1000;
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
    public static int getSkillPointMax() {return skillPointMax;}

    /**
     * Used to calculate the number of skill points the player has
     * @return the number of skill points allocated
     */
    public int skillPointSum() {
        return pilot + fighter + trader + engineer;
    }

}
