package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.event.TraderEvent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
/**
 * Class for BuySellTraderViewModel
 */
public class BuySellTraderViewModel extends ViewModel {
    public enum ShopMode {
        BUY, SELL
    }

    private final List<TradeGoodInfo> goods;
    /**
     * Creates a BuySellTraderViewModel
     */
    public BuySellTraderViewModel() {
        goods = new ArrayList<>();

        TraderEvent trader = (TraderEvent) GameState.getState().getPlayer().getCurrentEvent();
        LinkedHashMap<TradeGood, Integer> quantities = trader.getQuantities();
        LinkedHashMap<TradeGood, Integer> prices = trader.getPrices();

        for (TradeGood good : quantities.keySet()) {
            int quantity = quantities.get(good);
            int shipQuantity = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(good);
            int price = prices.get(good);
            TradeGoodInfo displayedGood = new TradeGoodInfo(good.getName(), price, quantity, shipQuantity, good);
            goods.add(displayedGood);
        }
    }
    /**
     * Performs a transaction
     * @param mode the shop mode
     * @param goodIndex the index of the trade good to perform a transaction on
     * @param quantity how much of the good to change
     * @return boolean whether or not the action was completed
     */
    public boolean performShopAction(ShopMode mode, int goodIndex, int quantity) {
        Player player = GameState.getState().getPlayer();
        TradeGood good = TradeGood.values()[goodIndex];
        switch (mode) {
            case BUY:
                if (player.getCurrentSystem().decreaseQuantity(good, quantity)) {
                    if (player.removeCredits(good.getPrice(player.getCurrentSystem()) * quantity)) {
                        if (player.getShip().addToInventory(good, quantity)) {
                            TradeGoodInfo displayedGood = goods.get(goodIndex);
                            displayedGood.setQuantity(displayedGood.getQuantity() - quantity);
                            displayedGood.setShipQuantity(displayedGood.getShipQuantity() + quantity);
                            return true;
                        } else {
                            // Undo what we just did.
                            player.getCurrentSystem().increaseQuantity(good, quantity);
                            player.addCredits(good.getPrice(player.getCurrentSystem()) * quantity);
                        }
                    } else {
                        // Undo what we just did.
                        player.getCurrentSystem().increaseQuantity(good, quantity);
                    }
                }
                return false;
            case SELL:
                if (player.getShip().removeFromInventory(good, quantity)) {
                    player.addCredits(good.getPrice(player.getCurrentSystem()) * quantity);
                    player.getCurrentSystem().increaseQuantity(good, quantity);
                    TradeGoodInfo displayedGood = goods.get(goodIndex);
                    displayedGood.setQuantity(displayedGood.getQuantity() + quantity);
                    displayedGood.setShipQuantity(displayedGood.getShipQuantity() - quantity);
                    return true;
                }
                return false;
        }
        return false;
    }
    /**
     * Gets a list of goods
     * @return the list of trade goods
     */
    public List<TradeGoodInfo> getGoods() {
        return goods;
    }
    /**
     * Gets the total number of cargo bays a player's ship has
     * @return the total number of cargo bays
     */
    public int getTotalCargo() {
        return GameState.getState().getPlayer().getShip().getTotalCargoBays();
    }
    /**
     * Gets the available number of cargo bays a player's ship has
     * @return returns the number of available cargo bays
     *
     */
    public int getAvailableCargo() {
        return GameState.getState().getPlayer().getShip().getNumOpenCargoBays();
    }
    /**
     * Gets the amount of credits a player has
     * @return the amount of credits a player has
     */
    public int getCash() {
        return GameState.getState().getPlayer().getCredits();
    }
}

