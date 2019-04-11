package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.model.system.shop.ShopMode;
import com.example.spacetrader.model.system.shop.Transaction;

import java.util.LinkedHashMap;

public class BuySellViewModel extends ViewModel {

    LinkedHashMap<String, TradeGoodInfo> goods;

    public BuySellViewModel() {
        goods = new LinkedHashMap<>();

        SolarSystem playerSystem = GameState.getState().getPlayer().getCurrentSystem();
        LinkedHashMap<TradeGood, Integer> quantities = playerSystem.getQuantities();
        LinkedHashMap<TradeGood, Integer> prices = playerSystem.getPrices();

        for (TradeGood good : quantities.keySet()) {
            int quantity = quantities.get(good);
            int shipQuantity = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(good);
            int price = prices.get(good);
            TradeGoodInfo displayedGood = new TradeGoodInfo(good.getName(), price, quantity, shipQuantity, good);
            goods.put(good.getName(), displayedGood);
        }
    }

    public boolean buyItem(TradeGoodInfo good, int quantity) {
        Transaction transaction = new Transaction(ShopMode.BUY, good.getGood(), quantity);
        Player player = GameState.getState().getPlayer();
        if (player.performTransaction(transaction, player.getCurrentSystem()) == Transaction.Result.SUCCESS) {
            good.shipQuantity += quantity;
            good.quantity -= quantity;
            return true;
        }
        return false;
    }

    public boolean sellItem(TradeGoodInfo good, int quantity) {
        Transaction transaction = new Transaction(ShopMode.SELL, good.getGood(), quantity);
        Player player = GameState.getState().getPlayer();
        if (player.performTransaction(transaction, player.getCurrentSystem()) == Transaction.Result.SUCCESS) {
            good.shipQuantity -= quantity;
            good.quantity += quantity;
            return true;
        }
        return false;
    }

    public LinkedHashMap<String, TradeGoodInfo> getGoods() {
        return goods;
    }

    public int getTotalCargo() {
        return GameState.getState().getPlayer().getShip().getTotalCargoBays();
    }

    public int getAvailableCargo() {
        return GameState.getState().getPlayer().getShip().getNumOpenCargoBays();
    }

    public int getCash() {
        return GameState.getState().getPlayer().getCredits();
    }
}
