package com.example.spacetrader.model;

import com.example.spacetrader.model.system.ResourceBias;
import com.example.spacetrader.model.system.SolarSystem;

/**
 * Holds the 10 trade goods in the game, incluing their names, base prices, and if they
 * are illegal
 */
public enum TradeGood {

    WATER("Water", 0, 0, 2, 30, 3, 4, "drought",
            ResourceBias.LOTSOFWATER, ResourceBias.DESERT, 30, 50),
    FURS("Furs", 0,0, 0, 250, 10, 10, "cold",
            ResourceBias.RICHFAUNA, ResourceBias.LIFELESS, 230, 280),
    ORE("Ore", 2, 2, 3, 350, 20, 10, "war",
            ResourceBias.MINERALRICH, ResourceBias.MINERALPOOR, 350, 420),
    FOOD("Food", 1, 0, 1, 100, 5, 5, "cropFail",
            ResourceBias.RICHSOIL, ResourceBias.POORSOIL, 90, 160),
    GAMES("Games", 3, 1, 6, 250, -10, 5, "boredom",
            ResourceBias.ARTISTIC, null, 160, 270),
    FIREWARMS("Firearms", 3, 1, 5, 1250, -75, 100, "war",
            ResourceBias.WARLIKE, null, 600, 1100),
    MEDICINE("Medicine", 4, 1, 6, 650, -20, 10, "plague",
            ResourceBias.LOTSOFHERBS, null, 400, 700),
    NARCOTICS("Narcotics", 5, 0, 5, 3500, -125, 150, "boredom",
            ResourceBias.WEIRDMUSHROOMS, null, 2000, 3000),
    ROBOTS("Robots", 6, 4, 7, 5000, -150, 100, "lackOfWorkers",
            null, null, 3500, 5000),
    MACHINES("Machines", 4, 3, 5, 900, -30, 5, "lackOfWorkers",
                     null,null, 600,800);

    private String name;
    private int MTLP;
    private int MTLU;
    private int TPP;
    private int basePrice;
    private int IPL;
    private int var;
    private String IE;
    private ResourceBias CR;
    private ResourceBias ER;
    private int MTL;
    private int MTH;

    /**
     * Initializes a trade good
     * @param name the name of the trade good
     * @param MTLP the minimum tech level on a planet to buy the resource
     * @param MTLU the minimum tech level on a planet to sell the resource
     * @param TPP the tech level that produces the most of the good
     * @param basePrice the default price of the good
     * @param IPL the price increase per tech level
     * @param var percentage price can vary above and below the base
     * @param IE event that changes the price of an item
     * @param CR planet condition that makes the good cheap
     * @param ER planet condition that makes the good expensive
     * @param MTL lowest price a space trader can value it
     * @param MTH highest price a space trader can value it
     */

    TradeGood(String name, int MTLP, int MTLU, int TPP, int basePrice, int IPL, int var, String IE,
              ResourceBias CR, ResourceBias ER, int MTL, int MTH) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TPP = TPP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = var;
        this.IE = IE;
        this.CR = CR;
        this.ER = ER;
        this.MTL = MTL;
        this.MTH = MTH;
    }
        
    //Price for space traders also needs to be implemented when we add random events.
    /**
     * returns the price of each good depending on the planets factors
     * @param planet represents the current planet the player is on
     * @return int representation of the items price
     */
    public int getPrice(SolarSystem planet) {
        int price = basePrice;
        int range = basePrice * (double)(var / 100)
        int maxPrice = basePrice + range;
        int minPrice = basePrice - range;

        //the higher the tech level, the more expensive the good.
        if (planet.getTechLevel().level > 0) {
            price += IPL * planet.getTechLevel().level;
        }

        //checks if planets resource makes the item cheaper
        if (planet.getResourceBias() == CR) {
            price -= Math.random() * var;
        }

        //checks if planets resource makes the item more expensive
        if (planet.getResourceBias() == ER) {
            price += Math.random() * var;
        }

        //compares IE of planet to the IE that effects the price of the good
        if (IE == planet.getIE()) {
            price = price + Math.random() * range;
        }
        //prevents the price from exceeding the set max price or falling below the set min price
        if (price > maxPrice) {
            price = maxPrice;
        } else if (price < minPrice || price < 0) {
            price = minPrice;
        }

        return price;
    }

    public String getName() {
        return name;
    }
}

