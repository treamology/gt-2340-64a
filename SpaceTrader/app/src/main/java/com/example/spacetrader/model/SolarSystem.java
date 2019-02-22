package com.example.spacetrader.model;

public abstract class SolarSystem {
    String name;
    int locationX;
    int locationY;
    int techLevel;
    int resource;

    final int[] techLevels = new int[]{0,1,2,3,4,5,6,7};
    final int[] resources = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12};

    public SolarSystem(String name, int x, int y) {
        this.name = name;
        techLevel = (int)(Math.random() * 7);
        resource = (int)(Math.random() * 12);
        locationX = x;
        locationY = y;
    }

    public int getTechLevel() {
        return techLevel;
    }
    public int getResource() {
        return resource;
    }
    public String getName() {
        return name;
    }
}
