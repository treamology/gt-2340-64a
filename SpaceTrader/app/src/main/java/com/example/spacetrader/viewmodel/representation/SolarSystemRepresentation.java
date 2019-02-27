package com.example.spacetrader.viewmodel.representation;

public class SolarSystemRepresentation {
    public int x;
    public int y;
    public String name;
    public boolean visited;
    public boolean currentlyVisiting;

    public SolarSystemRepresentation(int x, int y, String name, boolean visited, boolean currentlyVisiting) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.visited = visited;
        this.currentlyVisiting = currentlyVisiting;
    }
}
