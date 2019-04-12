package com.example.spacetrader.viewmodel;

/**
 * Class for SolarSystem Info
 */
public class SolarSystemInfo {
    public final String name;
    public final int x;
    public final int y;
    public final String techLevel;
    public final String resources;
    public final int index;

    /**
     * Initializes the system info
     * @param name the system name
     * @param x the system's x-coordinate
     * @param y the system's y-coordinate
     * @param techLevel the tech level of the system
     * @param resources the resources of the system
     * @param index the index of the system
     */
    public SolarSystemInfo(String name, int x, int y, String techLevel, String resources, int index) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.techLevel = techLevel;
        this.resources = resources;
        this.index = index;
    }
}
