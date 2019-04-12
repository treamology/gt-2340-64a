package com.example.spacetrader.model.system;
/**
 * Enum of PriceIncreaseEvent
 */
public enum PriceIncreaseEvent {
    NONE ("None"),
    DROUGHT ("Drought"),
    COLD ("Cold"),
    CROPFAIL ("Cropfail"),
    WAR ("War"),
    BOREDOM ("Boredom"),
    PLAGUE ("Plague"),
    LACKOFWORKERS ("Lack of Workers");

    public final String name;
    /**
     * Sets the name of the PriceIncreaseEvent
     * @param name the name to set the PriceIncreaseEvent name to
     */
    PriceIncreaseEvent(String name) {
        this.name = name;
    }
}
