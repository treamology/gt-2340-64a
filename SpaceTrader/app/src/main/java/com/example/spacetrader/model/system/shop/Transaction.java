package com.example.spacetrader.model.system.shop;

import com.example.spacetrader.model.TradeGood;

public class Transaction {
    public enum Result {
        SUCCESS, NOT_ENOUGH_MONEY, INVENTORY_FULL, NO_GOODS_LEFT, NO_ITEMS_TO_SELL
    }
    public final ShopMode type;
    public final TradeGood good;
    public final int quantity;

    public Transaction(ShopMode type, TradeGood good, int quantity) {
        this.type = type;
        this.good = good;
        this.quantity = quantity;
    }
}
