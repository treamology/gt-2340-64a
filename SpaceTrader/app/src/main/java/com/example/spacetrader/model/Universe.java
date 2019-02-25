package com.example.spacetrader.model;

import com.example.spacetrader.model.system.Planet;
import com.example.spacetrader.model.system.PlanetGeneration;
import com.example.spacetrader.model.system.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Holds the world map and all systems in it
 */
public class Universe {

    private static ArrayList<Planet> planets;
    private static final int sizeX = 150; //sizeX and sizeY are the maximum coordinates of the map
    private static final int sizeY = 100;
    private static HashSet<Position> takenPositions;

    public static void createUniverse () {
        PlanetGeneration.clearNames();
        takenPositions = new HashSet<>();
        planets = new ArrayList<>();
        for (int i = 0; i < Planet.getNumPlanets(); i++) {
            Planet current = new Planet();
            planets.add(current);
        }
    }

    public static Position generatePosition() {
        Random rng = new Random();
        int x = rng.nextInt(sizeX);
        int y = rng.nextInt(sizeY);
        Position pos = new Position(x, y);
        if (takenPositions.contains(pos)) {
            return generatePosition();
        } else {
            takenPositions.add(pos);
            return pos;
        }
    }
}
