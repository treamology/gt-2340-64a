package com.example.spacetrader.model.system;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Universe;

public abstract class SolarSystem {

    String name;
    Position position;
    TechLevel techLevel;
    ResourceBias resourceBias;

    public SolarSystem(String name, int x, int y) {
        this.name = name;
        techLevel = TechLevel.values()[GameState.rng.nextInt(TechLevel.values().length)];
        resourceBias = ResourceBias.values()[GameState.rng.nextInt(ResourceBias.values().length)];
        position = Universe.generatePosition();
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }
    public ResourceBias getResourceBias() {
        return resourceBias;
    }
    public String getName() {
        return name;
    }
    public Position getPosition() {
        return position;
    }
}
