package com.example.spacetrader.model;

/**
 * Holds the 10 trade goods in the game, incluing their names, base prices, and if they
 * are illegal
 */
public enum TradeGood {

    WATER("Water", 40, false),
    FURS("Furs", 280, false),
    FOOD("Food", 120, false),
    ORE("Ore", 340, false),
    GAMES("Games", 210, false),
    FIREWARMS("Firearms", 950, true),
    MEDICINE("Medicine", 570, false),
    MACHINES("Machines", 750, false),
    NARCOTICS("Narcotics", 3100, true),
    ROBOTS("Robots", 4200, false);

    private String type;
    private int baseCost;
    private boolean illegal;

    TradeGood(String type, int baseCost, boolean illegal) {
        this.type = type;
        this.baseCost = baseCost;
        this.illegal = illegal;
    }
}
