package com.example.spacetrader.viewmodel;

public class DisplayedSolarSystem {
    public final int x;
    public final int y;
    public final String name;
    public final boolean visited;
    public final boolean currentlyVisiting;

    public DisplayedSolarSystem(int x, int y, String name, boolean visited, boolean currentlyVisiting, int imageIndex) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.visited = visited;
        this.currentlyVisiting = currentlyVisiting;
        int imageIndex1 = imageIndex;
    }
}
