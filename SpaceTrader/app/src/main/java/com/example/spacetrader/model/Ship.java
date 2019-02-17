package com.example.spacetrader.model;

/**
 * Class that holds a spaceship. The different ship types are subclasses
 */
public abstract class Ship {

    String type;

    public Ship () {}

    @Override
    public String toString() {
        return type;
    }
}
