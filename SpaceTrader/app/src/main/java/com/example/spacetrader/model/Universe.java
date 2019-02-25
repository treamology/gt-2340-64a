package com.example.spacetrader.model;

import com.example.spacetrader.model.system.Planet;
import com.example.spacetrader.model.system.PlanetGeneration;
import com.example.spacetrader.model.system.Position;
import com.example.spacetrader.model.system.ResourceBias;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.model.system.TechLevel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Holds the world map and all systems in it
 */
public class Universe {

    private static ArrayList<SolarSystem> systems;
    private static final int sizeX = 150; //sizeX and sizeY are the maximum coordinates of the map
    private static final int sizeY = 100;
    private static HashSet<Position> takenPositions;
    private static HashSet<Integer> takenNames;

    public static void createUniverse() {
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
    private static SolarSystem generateSystem() {
        TechLevel techLevel = TechLevel.values()[GameState.rng.nextInt(TechLevel.values().length)];
        ResourceBias resourceBias = ResourceBias.values()[GameState.rng.nextInt(ResourceBias.values().length)];
        Position position = generateSystemPosition();
        String name = generateSystemName();
        return new SolarSystem(name, position, techLevel, resourceBias);
    }

    public static Position generateSystemPosition() {
        int x = GameState.rng.nextInt(sizeX);
        int y = GameState.rng.nextInt(sizeY);
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
    public static String generateSystemName() {
        String name = null;
        int index = GameState.rng.nextInt(SolarSystem.NAMES.length);
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
}
