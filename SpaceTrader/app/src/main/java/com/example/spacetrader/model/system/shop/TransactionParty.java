package com.example.spacetrader.model.system.shop;

import com.example.spacetrader.model.TradeGood;
/**
 * Interface for transaction parties
 * */
public interface TransactionParty {
    /**
     * Gets the quantity of a trade good
     * @param good the trade good to check quantity of
     * @return the quantity of the trade good
     */
    int getInventoryQuantity(TradeGood good);

    /**
     * Changes the inventory quantity of a trade good by an amount
     * @param good the trade good to change quantity of
     * @param amount the amount to change the quantity by
     */
    void changeInventoryQuantityByAmount(TradeGood good, int amount);

    /**
     * Generates goods and prices for a transaction party
     */
    void generateGoodsAndPrices();

    /**
     * Gets the cost to buy a trade good
     * @param good the good to check the buy price of
     * @return the price to buy the good
     */
    int getBuyPrice(TradeGood good);

    /**
     * Gets the cost to sell a trade good
     * @param good the good to check the sell price of
     * @return the price to sell the good
     */
    int getSellPrice(TradeGood good);
}
