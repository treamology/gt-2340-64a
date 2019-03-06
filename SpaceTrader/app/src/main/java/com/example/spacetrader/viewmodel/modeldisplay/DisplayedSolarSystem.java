package com.example.spacetrader.viewmodel.modeldisplay;

public class DisplayedSolarSystem {
    public int x;
    public int y;
    public String name;
    public boolean visited;
    public boolean currentlyVisiting;
    public int imageIndex;

    public DisplayedSolarSystem(int x, int y, String name, boolean visited, boolean currentlyVisiting, int imageIndex) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.visited = visited;
        this.currentlyVisiting = currentlyVisiting;
        this.imageIndex = imageIndex;
    }
}
