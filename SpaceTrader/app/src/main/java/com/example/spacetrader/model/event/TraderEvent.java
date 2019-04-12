package com.example.spacetrader.model.event;


import android.util.Log;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.TradeGood;

import java.util.LinkedHashMap;
import java.util.Random;

/**
 * You encounter a trader which acts just like a planet marketplace, but you
 * can attack the trader too
 */
public class TraderEvent extends Event {

    private final LinkedHashMap<TradeGood, Integer> quantities;
    private final LinkedHashMap<TradeGood, Integer> prices;
    /**
     * The trader event
     */
    public TraderEvent() {
        this.description = "You find a space trader";

        quantities = new LinkedHashMap<>();
        prices = new LinkedHashMap<>();
    }

    @Override
    public void run() {
        name = "trader";
        Log.d("Random Event", "you ran a trader event");
        this.description = "A trader has docked to your ship, what would you like to do?";
    }

    @Override
    public void attack() {
        Log.d("Random Event", "you attacked the trader");
        int difficulty = GameState.getState().rng.nextInt(12) + 1;
        if (GameState.getState().getPlayer().getFighter() >= difficulty) {
            if (GameState.getState().getPlayer().getShip().getNumOpenCargoBays() > 0) {
                int max = GameState.getState().getPlayer().getShip().getNumOpenCargoBays();
                int numItems = GameState.getState().rng.nextInt(max) + 1;
                TradeGood stolenItem = randomGoodSeized();
                consequence = "You have overpowered the trader and plundered his goods. You stole " + numItems + " of " + stolenItem + " from his cargo bay.";
                GameState.getState().getPlayer().getShip().addToInventory(stolenItem, numItems);
            } else {
                consequence = "You have defeated the trader. Unfortunately, your cargo bays are full so you couldn't steal any of his goods.";
            }
        } else {
            if (GameState.getState().getPlayer().getShip().getTotalCargoBays() - GameState.getState().getPlayer().getShip().getNumOpenCargoBays() != 0) {
                TradeGood stolenItem = randomGoodSeized();
                int numItems = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(stolenItem);
                if (numItems > 0) {
                    consequence = "You were no match for the trader. He was prepared for a battle. Before he escaped, he stole " + numItems + " of " + stolenItem + "from your cargo bay.";
                    GameState.getState().getPlayer().getShip().removeFromInventory(stolenItem, numItems);
                } else {
                    int creds = GameState.getState().getPlayer().getCredits();
                    creds = creds/6;
                    consequence = "The trader overpowered you. Before he escaped, he stole " + creds + " from your wallet";
                    GameState.getState().getPlayer().removeCredits(creds);
                }
            } else {
                int creds = GameState.getState().getPlayer().getCredits();
                creds = creds/6;
                consequence = "The trader overpowered you. Before he escaped, he stole " + creds + " from your wallet";
                GameState.getState().getPlayer().removeCredits(creds);
            }
        }
    }

    @Override
    public void flee() {
        Log.d("Random Event", "you left the trader");
        this.consequence = "Looks like you aren't interested in trading, the trader has left.";
    }

    @Override
    public void talk() {
        this.consequence = "The trader enthusiastically shows you his wares.";
        Log.d("Random Event", "you opened the trade menu");
        generateNewTradeGoods();
    }

    private void generateNewTradeGoods() {
        Random rand = GameState.getState().rng;

        switch (rand.nextInt(10)) {
            case 0:
                TradeGood water = TradeGood.WATER;
                int waterNum = rand.nextInt(20) + 1;
                int waterPrice = water.getPrice();
                quantities.put(water, waterNum);
                prices.put(water, waterPrice);
                break;
            case 1:
                TradeGood furs = TradeGood.FURS;
                int furNum = rand.nextInt(20) + 1;
                int fursPrice = furs.getPrice();
                quantities.put(furs, furNum);
                prices.put(furs, fursPrice);
                break;
            case 2:
                TradeGood food = TradeGood.FOOD;
                int foodNum = rand.nextInt(20) + 1;
                int foodPrice = food.getPrice();
                quantities.put(food, foodNum);
                prices.put(food, foodPrice);
                break;
            case 3:
                TradeGood ore = TradeGood.ORE;
                int oreNum = rand.nextInt(20) + 1;
                int orePrice = ore.getPrice();
                quantities.put(ore, oreNum);
                prices.put(ore, orePrice);
                break;
            case 4:
                TradeGood games = TradeGood.GAMES;
                int gamesNum = rand.nextInt(20) + 1;
                int gamesPrice = games.getPrice();
                quantities.put(games, gamesNum);
                prices.put(games, gamesPrice);
                break;
            case 5:
                TradeGood firearms = TradeGood.FIREARMS;
                int firearmsNum = rand.nextInt(20) + 1;
                int firearmsPrice = firearms.getPrice();
                quantities.put(firearms, firearmsNum);
                prices.put(firearms, firearmsPrice);
                break;
            case 6:
                TradeGood medicine = TradeGood.MEDICINE;
                int medicineNum = rand.nextInt(20) + 1;
                int medicinePrice = medicine.getPrice();
                quantities.put(medicine, medicineNum);
                prices.put(medicine, medicinePrice);
                break;
            case 7:
                TradeGood machines = TradeGood.MACHINES;
                int machinesNum = rand.nextInt(20) + 1;
                int machinesPrice = machines.getPrice();
                quantities.put(machines, machinesNum);
                prices.put(machines, machinesPrice);
                break;
            case 8:
                TradeGood narcotics = TradeGood.NARCOTICS;
                int narcoticsNum = rand.nextInt(20) + 1;
                int narcoticsPrice = narcotics.getPrice();
                quantities.put(narcotics, narcoticsNum);
                prices.put(narcotics, narcoticsPrice);
                break;
            case 9:
                TradeGood robots = TradeGood.ROBOTS;
                int robotsNum = rand.nextInt(20) + 1;
                int robotsPrice = robots.getPrice();
                quantities.put(robots, robotsNum);
                prices.put(robots, robotsPrice);
                break;
        }
    }
    private TradeGood randomGoodSeized() {
        Random rand = GameState.getState().rng;

        switch (rand.nextInt(10)) {
            case 0:
                return TradeGood.WATER;
            case 1:
                return TradeGood.FURS;
            case 2:
                return TradeGood.FOOD;
            case 3:
                return TradeGood.ORE;
            case 4:
                return TradeGood.GAMES;
            case 5:
                return TradeGood.FIREARMS;
            case 6:
                return TradeGood.MEDICINE;
            case 7:
                return TradeGood.MACHINES;
            case 8:
                return TradeGood.NARCOTICS;
            case 9:
                return TradeGood.ROBOTS;
        }
        return null;
    }
    /**
     * Gets the quantities of the trade goods
     * @return a hashmap of the trade good quantities
     */
    public LinkedHashMap<TradeGood, Integer> getQuantities() {
        return quantities;
    }

    /**
     * Gets the prices of the trade goods
     * @return a hashmap of the trade good prices
     */
    public LinkedHashMap<TradeGood, Integer> getPrices() {
        return prices;
    }

    /**
     * Decreases the quantity of a trade good
     * @param good the good to decrease quantity of
     * @param quantity how much to decrease the quantity by
     * @return whether or not it was successful
     */
    public boolean decreaseQuantity(TradeGood good, int quantity) {
        if (quantities.get(good) - quantity >= 0) {
            quantities.put(good, quantities.get(good) - quantity);
            return true;
        }
        return false;
    }
    /**
     * Increases the quantity of a trade good
     * @param quantity the amount to increase by
     * @param good the good to increase quantity of
     */
    public void increaseQuantity(TradeGood good, int quantity) {
        quantities.put(good, quantities.get(good) + quantity);
    }
}
