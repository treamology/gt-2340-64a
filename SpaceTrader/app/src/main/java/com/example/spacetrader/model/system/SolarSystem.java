package com.example.spacetrader.model.system;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Universe;

import java.util.HashSet;
import java.util.Random;

public class SolarSystem {

    public static final int MAX_SYSTEMS = 10;
    public static final String[] NAMES = new String[]{"Acamar", "Adahn", "Aldea", "Campor", "Deneb"
            , "Endor", "Hulst", "Janus", "Ligon", "Melina", "Relva", "Sol", "Antedi", "Balonsee"
            , "Baratas", "Brax", "Bretel", "Calondia", "Capelle", "Carzon", "Castor", "Cestus"
            , "Cheron", "Courteny", "Dale", "Damast", "Davlos", "Deneva", "Devidia"
            , "Draylon", "Drema", "Esmee", "Exo", "Ferris", "Festen", "Fourmi", "Frolix", "Gemulon"
            , "Guinifer", "Hades", "Hamlet", "Helena", "Iodine", "Iralius", "Japori", "Jarada"
            , "Jason", "Kaylon", "Khefka", "Kira", "Klaatu", "Klaestron", "Korma", "Kravat", "Krios"
            , "Laertes", "Largo", "Lave", "Lowry", "Magrat", "Malcoria", "Mentar", "Merik"
            , "Mintaka", "Montor", "Mordan", "Myrthe", "Nelvana", "Nix", "Nyle", "Odet", "Og"
            , "Omega", "Omphalos", "Oria", "Othello", "Parade", "Penthara", "Picard", "Pollux"
            , "Quator", "Rakhar", "Ran", "Regulus", "Rhymus", "Rochani", "Rubicum", "Rutia"
            , "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Somari", "Stakoron", "Styris", "Telani"
            , "Tamus", "Tantalos", "Tarnuga", "Tarkhannon", "Terosa", "Thera", "Titan", "Torin"
            , "Triacus", "Turkana", "Tyrus", "Umberlee", "Utopia", "Vadera", "Vagra", "Vandor"
            , "Ventax", "Xenon", "Xerxes", "Yew", "Yojimbo", "Zalkon", "Zuul"};



    String name;
    Position position;
    TechLevel techLevel;
    ResourceBias resourceBias;
    boolean visited;

    public SolarSystem(String name, Position position, TechLevel techLevel, ResourceBias resourceBias) {
        this.name = name;
        this.position = position;
        this.techLevel = techLevel;
        this.resourceBias = resourceBias;
        this.visited = false;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }
    public ResourceBias getResourceBias() {
        return resourceBias;
    }
    public String getName() {
        return name;
    }
    public Position getPosition() {
        return position;
    }
    public boolean getVisited() { return visited; }
}
