package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.event.TraderEvent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BuySellTraderViewModel extends ViewModel {
    public enum ShopMode {
        BUY, SELL
    }

    List<TradeGoodInfo> goods;

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

    public List<TradeGoodInfo> getGoods() {
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

