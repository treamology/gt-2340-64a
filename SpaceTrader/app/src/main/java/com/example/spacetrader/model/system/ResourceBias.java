package com.example.spacetrader.model.system;

public enum ResourceBias {

    NOSPECIALRESOURCES ("No Special Resources"),
    MINERALRICH ("Mineral-rich"),
    MINERALPOOR ("Mineral-poor"),
    DESERT ("Desert"),
    LOTSOFWATER ("Lots of water"),
    RICHSOIL ("Rich Soil"),
    POORSOIL ("Poor Soil"),
    RICHFAUNA ("Rich Fauna"),
    LIFELESS ("Lifeless"),
    WEIRDMUSHROOMS ("Weird Mushrooms"),
    LOTSOFHERBS ("Lots of Herbs"),
    ARTISTIC ("Artistic"),
    WARLIKE ("Warlike");

    public final String name;

    ResourceBias(String name) {
        this.name = name;
    }
}
