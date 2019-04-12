package com.example.spacetrader.model.system;
/**
 * Enum of ResourceBias
 */
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
    /**
     * Sets the name of the ResourceBias
     * @param name the name to set the ResourceBias name to
     */
    ResourceBias(String name) {
        this.name = name;
    }
}
