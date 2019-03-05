package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.viewmodel.modeldisplay.DisplayedTradeGood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BuySellViewModel extends ViewModel {
    List<DisplayedTradeGood> goods;

    public BuySellViewModel() {
        goods = new ArrayList<>();

        SolarSystem playerSystem = GameState.getState().getPlayer().getCurrentSystem();
        LinkedHashMap<TradeGood, Integer> quantities = playerSystem.getQuantities();
        LinkedHashMap<TradeGood, Integer> prices = playerSystem.getPrices();

        for (TradeGood good : quantities.keySet()) {
            int quantity = quantities.get(good);
            int price = prices.get(good);
            DisplayedTradeGood displayedGood = new DisplayedTradeGood(good.getName(), price, quantity);
            goods.add(displayedGood);
        }
    }

    public List<DisplayedTradeGood> getGoods() {
        return goods;
    }
}
