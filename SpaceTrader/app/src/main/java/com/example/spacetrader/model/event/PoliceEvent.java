package com.example.spacetrader.model.event;

import android.util.Log;

/**
 * Police stop your ship and may ask to search it or arrest you (if a criminal)
 */
public class PoliceEvent extends Event {

    public PoliceEvent() {
        this.description = "You encounter police";
    }

    @Override
    public void run() {
        Log.d("Random Event", "you ran a police event");
    }

    @Override
    public void attack() {
        Log.d("Random Event", "you attacked the police");
    }

    @Override
    public void flee() {
        Log.d("Random Event", "you ran from the police");
    }

    @Override
    public void talk() {
        Log.d("Random Event", "you let the police search your ship");
    }
}
