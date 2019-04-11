package com.example.spacetrader.model.event;

import com.example.spacetrader.model.GameState;

public abstract class Event {

    String description;
    String consequence;
    String name;

    /**
     * Executes the random event
     */
    public abstract void run();

    /**
     * Attack the other ship
     */
    public  abstract  void attack();

    /**
     * Attempt to leave the ship's vicinity
     */
    public abstract void flee();

    /**
     * Trade for traderEvent, submit to pirate demands or police searching
     */
    public abstract void talk();

    public static Event randomEvent() {
        int seed = GameState.getState().rng.nextInt(3);
        if (seed == 0) {
            return new TraderEvent();
        } else if (seed == 1) {
            return new PirateEvent();
        } else if (seed == 2) {
            return new PoliceEvent();
        } else {
            return null;
        }
    }

    public String getDescription() {
        return description;
    }
    public String getName() { return name; }
    public String getConsequence() {
        return consequence;
    }
}
