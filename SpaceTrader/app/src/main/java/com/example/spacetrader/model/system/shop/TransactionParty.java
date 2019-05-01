package com.example.spacetrader.model.system.shop;

import com.example.spacetrader.model.TradeGood;

public interface TransactionParty {
    int getInventoryQuantity(TradeGood good);
    void changeInventoryQuantityByAmount(TradeGood good, int amount);

    void generateGoodsAndPrices();
    int getBuyPrice(TradeGood good);
    int getSellPrice(TradeGood good);
}
