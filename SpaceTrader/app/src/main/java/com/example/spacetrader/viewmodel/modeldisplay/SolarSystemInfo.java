package com.example.spacetrader.viewmodel.modeldisplay;

public class SolarSystemInfo {
    public String name;
    public int x;
    public int y;
    public String techLevel;
    public String resources;

    public SolarSystemInfo(String name, int x, int y, String techLevel, String resources) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.techLevel = techLevel;
        this.resources = resources;
    }
}
