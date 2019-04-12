package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.model.system.shop.ShopMode;
import com.example.spacetrader.model.system.shop.Transaction;

import java.util.LinkedHashMap;
/**
 * Class for the BuySellViewModel
 */
public class BuySellViewModel extends ViewModel {

    private final LinkedHashMap<String, TradeGoodInfo> goods;
    /**
     * Creates the BuySellViewModel
     */
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
    /**
     * Player buys an item
     * @param good the good a player is trying to buy
     * @param quantity the amount of a good a player is trying to buy
     */
    public void buyItem(TradeGoodInfo good, int quantity) {
        Transaction transaction = new Transaction(ShopMode.BUY, good.getGood(), quantity);
        Player player = GameState.getState().getPlayer();
        if (player.performTransaction(transaction, player.getCurrentSystem()) == Transaction.Result.SUCCESS) {
            good.shipQuantity += quantity;
            good.quantity -= quantity;
        }
    }
    /**
     * Player sells an item
     * @param good the good a player is trying to sell
     * @param quantity the amount of a good a player is trying to sell
     */
    public void sellItem(TradeGoodInfo good, int quantity) {
        Transaction transaction = new Transaction(ShopMode.SELL, good.getGood(), quantity);
        Player player = GameState.getState().getPlayer();
        if (player.performTransaction(transaction, player.getCurrentSystem()) == Transaction.Result.SUCCESS) {
            good.shipQuantity -= quantity;
            good.quantity += quantity;
        }
    }
    /**
     * Gets the goods
     * @return a linkedHashMap of the trade goods
     */
    public LinkedHashMap<String, TradeGoodInfo> getGoods() {
        return goods;
    }

    /**
     * Gets the totalCargo bays
     * @return the total cargo bays of a player
     */
    public int getTotalCargo() {
        return GameState.getState().getPlayer().getShip().getTotalCargoBays();
    }

    /**
     * Gets the available cargo bays
     * @return the available cargo bays of a player
     */
    public int getAvailableCargo() {
        return GameState.getState().getPlayer().getShip().getNumOpenCargoBays();
    }

    /**
     * Gets the player's credits
     * @return the amount of credits a player has
     */
    public int getCash() {
        return GameState.getState().getPlayer().getCredits();
    }
}
