package com.example.spacetrader.model.event;

import com.example.spacetrader.model.GameState;
/**
 * Event class
 */
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
    /**
     * Selects an event
     * @return the random event to be carried out
     */
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
    /**
     * Gets the event description
     * @return the event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the event name
     * @return the event name
     */
    public String getName() { return name; }
    /**
     * Gets the event consequence
     * @return the event consequence
     */
    public String getConsequence() {
        return consequence;
    }
}
