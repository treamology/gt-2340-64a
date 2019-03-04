package com.example.spacetrader.model.system;

public enum TechLevel {
    PREAGRICULTURAL (0, "Pre-agricultural"),
    AGRICULTURAL (1, "Agricultural"),
    MEDIEVAL (2, "Medieval"),
    RENAISSANCE (3, "Renaissance"),
    EARLYINDUSTRIAL (4, "Early Industrial"),
    INDUSTRIAL (5, "Industrial"),
    POSTINDUSTRIAL (6, "Post-industrial"),
    HITECH (7, "High Tech");

    public final int level;
    public final String name;
    TechLevel(int level, String name) {
        this.level = level;
        this.name = name;
    }
}

