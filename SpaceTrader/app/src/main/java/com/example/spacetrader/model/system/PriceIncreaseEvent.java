package com.example.spacetrader.model.system;

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

    PriceIncreaseEvent(String name) {
        this.name = name;
    }
}
