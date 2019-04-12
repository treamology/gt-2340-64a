package com.example.spacetrader.model.system.shop;

import com.example.spacetrader.model.TradeGood;
/**
 * Transaction class
 */
public class Transaction {
    /**
     * Result enum- the result of the transaction
     */
    public enum Result {
        SUCCESS, NOT_ENOUGH_MONEY, INVENTORY_FULL, NO_GOODS_LEFT, NO_ITEMS_TO_SELL
    }
    public final ShopMode type;
    public final TradeGood good;
    public final int quantity;
    /**
     * Initializes the transaction given parameters
     * @param type the type of shop
     * @param good the tradegood being transacted
     * @param quantity the amount of trade good being transacted
     */
    public Transaction(ShopMode type, TradeGood good, int quantity) {
        this.type = type;
        this.good = good;
        this.quantity = quantity;
    }
}
