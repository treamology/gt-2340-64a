package com.example.spacetrader.model.event;

import android.util.Log;

import com.example.spacetrader.model.TradeGood;

import java.util.LinkedHashMap;

/**
 * Pirates demand some of your trade goods or they attack
 */
public class PirateEvent extends Event {

    LinkedHashMap<TradeGood, Integer> demands;

    public PirateEvent() {
        this.description = "You are beset by pirates";
    }

    @Override
    public void run() {
        Log.d("Random Event", "you ran a pirate event");
    }

    @Override
    public void attack() {
        Log.d("Random Event", "you fought the pirate");
    }

    @Override
    public void flee() {
        Log.d("Random Event", "you tried to escape the pirate");
    }

    @Override
    public void talk() {
        Log.d("Random Event", "you gave in to the pirate's demands");
    }
}
