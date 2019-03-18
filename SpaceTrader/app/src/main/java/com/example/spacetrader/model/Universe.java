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
    private static final int maxDistance = 30;
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
        Position previousLocation = null;
        for (int i = 0; i < SolarSystem.MAX_SYSTEMS; i++) {
            SolarSystem current = generateSystem(previousLocation);
            systems.add(current);
            previousLocation = current.getPosition();
        }
    }

    /**
     * Generates a single solar system.
     *
     * @return A new, randomly generated solar system.
     */
    private SolarSystem generateSystem(Position previousLocation) {
        GameState game = GameState.getState();

        TechLevel techLevel = TechLevel.values()[game.rng.nextInt(TechLevel.values().length)];
        ResourceBias resourceBias = ResourceBias.values()[game.rng.nextInt(ResourceBias.values().length)];
        Position position = generateSystemPosition(previousLocation);
        String name = generateSystemName();

        Log.d("APP", String.format("Generated system with name %s and position (%d, %d).", name, position.getX(), position.getY()));

        return new SolarSystem(name, position, techLevel, resourceBias);
    }

    /**
     * Generates the location of a solar system within "jumping" distance to another
     * @param neighbor the solar system within the distance of this one, or null if this is the
     *                 first solar system created
     * @return the position of the new system
     */
    private Position generateSystemPosition(Position neighbor) {
        GameState game = GameState.getState();

        if (neighbor == null) {
            int x = game.rng.nextInt(SIZE_X);
            int y = game.rng.nextInt(SIZE_Y);
            Position pos = new Position(x, y);
            takenPositions.add(pos);
            return pos;
        }

        ArrayList<Position> choices = new ArrayList<>();
        for (int i = Math.max(0, neighbor.getX() - maxDistance); i <= Math.min(SIZE_X, neighbor.getX() + maxDistance); i++) {
            for (int j = Math.max(0, neighbor.getY() - maxDistance); j <= Math.min(SIZE_Y, neighbor.getY() + maxDistance); j++) {
                if (Math.sqrt(Math.pow((double) (i - neighbor.getX()), 2) +
                        Math.pow((double) (j - neighbor.getY()), 2)) <= maxDistance) {
                    choices.add(new Position(i, j));
                }
            }
        }
        int index = game.rng.nextInt(choices.size());
        while (takenPositions.contains(choices.get(index))) {
            index = game.rng.nextInt(choices.size());
        }
        takenPositions.add(choices.get(index));
        return choices.get(index);
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
