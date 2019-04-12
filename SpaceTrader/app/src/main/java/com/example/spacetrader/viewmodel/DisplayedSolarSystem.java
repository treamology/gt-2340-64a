package com.example.spacetrader.viewmodel;
/**
 * The Displayed Solar System class
 */
public class DisplayedSolarSystem {
    public final int x;
    public final int y;
    public final String name;
    public final boolean visited;
    public final boolean currentlyVisiting;

    /**
     * Initializes the solar system
     * @param x the x coordinate of the solar system
     * @param y the y coordinate of the solar system
     * @param name the name of the solar system
     * @param visited whether or not the system has been visited
     * @param currentlyVisiting whether or not the player is currently visiting the system
     * @param imageIndex the index of the image of the system
     */
    public DisplayedSolarSystem(int x, int y, String name, boolean visited, boolean currentlyVisiting, int imageIndex) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.visited = visited;
        this.currentlyVisiting = currentlyVisiting;
    }
}
