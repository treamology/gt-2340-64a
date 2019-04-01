package com.example.spacetrader.model.event;


import android.util.Log;

import com.example.spacetrader.model.TradeGood;

import java.util.LinkedHashMap;

/**
 * You encounter a trader which acts just like a planet marketplace, but you
 * can attack the trader too
 */
public class TraderEvent extends Event {

    LinkedHashMap<TradeGood, Integer> quantities;
    LinkedHashMap<TradeGood, Integer> prices;

    public TraderEvent() {
        this.description = "You find a space trader";
    }

    @Override
    public void run() {
        Log.d("Random Event", "you ran a trader event");
    }

    @Override
    public void attack() {
        Log.d("Random Event", "you attacked the trader");
    }

    @Override
    public void flee() {
        Log.d("Random Event", "you left the trader");
    }

    @Override
    public void talk() {
        Log.d("Random Event", "you opened the trade menu");
    }
}
