package com.example.spacetrader.viewmodel;

public class SolarSystemInfo {
    public final String name;
    public final int x;
    public final int y;
    public final String techLevel;
    public final String resources;
    public final int index;

    public SolarSystemInfo(String name, int x, int y, String techLevel, String resources, int index) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.techLevel = techLevel;
        this.resources = resources;
        this.index = index;
    }
}
