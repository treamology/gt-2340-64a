package com.example.spacetrader.model;

import android.util.Log;

import com.example.spacetrader.model.system.Position;
import com.example.spacetrader.model.system.ResourceBias;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.model.system.TechLevel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Holds the world map and all systems in it
 */
public class Universe {

    public static final int SIZE_X = 150; //sizeX and sizeY are the maximum coordinates of the map
    public static final int SIZE_Y = 100;
    private List<SolarSystem> systems;
    private HashSet<Position> takenPositions;
    private HashSet<Integer> takenNames;

    /**
     * Generates a new universe with systems at various positions. This constructor shouldn't be
     * called directly, but rather through the creation of a new game.
     */
    Universe() {
        takenPositions = new HashSet<>();
        takenNames = new HashSet<>();
        systems = new ArrayList<>();
        for (int i = 0; i < SolarSystem.MAX_SYSTEMS; i++) {
            SolarSystem current = generateSystem();
            systems.add(current);
        }
    }

    /**
     * Generates a single solar system.
     *
     * @return A new, randomly generated solar system.
     */
    private SolarSystem generateSystem() {
        GameState game = GameState.getState();

        TechLevel techLevel = TechLevel.values()[game.rng.nextInt(TechLevel.values().length)];
        ResourceBias resourceBias = ResourceBias.values()[game.rng.nextInt(ResourceBias.values().length)];
        Position position = generateSystemPosition();
        String name = generateSystemName();

        Log.d("APP", String.format("Generated system with name %s and position (%d, %d).", name, position.getX(), position.getY()));

        return new SolarSystem(name, position, techLevel, resourceBias);
    }

    private Position generateSystemPosition() {
        GameState game = GameState.getState();

        int x = game.rng.nextInt(SIZE_X);
        int y = game.rng.nextInt(SIZE_Y);
        Position pos = new Position(x, y);
        if (takenPositions.contains(pos)) {
            return generateSystemPosition();
        } else {
            takenPositions.add(pos);
            return pos;
        }
    }

    /**
     * Randomly picks a name from the planet without allowing duplicates
     * @return the name
     */
    private String generateSystemName() {
        String name = null;
        int index = GameState.getState().rng.nextInt(SolarSystem.NAMES.length);
        int endLoop = index;
        do {
            if (!takenNames.contains(index)) {
                takenNames.add(index);
                name = SolarSystem.NAMES[index];
            } else {
                index = (index + 1) % SolarSystem.NAMES.length;
            }
        } while (name == null && index != endLoop);
        if (name == null) {
            name = "Unidentified planet";
        }
        return name;
    }

    public List<SolarSystem> getSystems() {
        return systems;
    }
}
