package com.example.spacetrader.model.system;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Universe;
import com.example.spacetrader.model.TradeGood;

import java.util.HashMap;
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
    HashMap quantity;
    boolean IE;

    public SolarSystem(String name, Position position, TechLevel techLevel, ResourceBias resourceBias) {
        this.name = name;
        this.position = position;
        this.techLevel = techLevel;
        this.resourceBias = resourceBias;
        this.visited = false;

        TradeGood water = TradeGood.WATER;
        TradeGood furs = TradeGood.FURS;
        TradeGood food = TradeGood.FOOD;
        TradeGood ore = TradeGood.ORE;
        TradeGood games = TradeGood.GAMES;
        TradeGood firearms = TradeGood.FIREWARMS;
        TradeGood medicine = TradeGood.MEDICINE;
        TradeGood machines = TradeGood.MACHINES;
        TradeGood narcotics = TradeGood.NARCOTICS;
        TradeGood robots = TradeGood.ROBOTS;


        Random rand = new Random();

        double level = techLevel.level;
        int levelInt = techLevel.level;

        double waterTech = (-((level-2) * (level-2)) + 140)/(140);
        int waterNum = (int) (rand.nextInt(20) * waterTech);

        double furTech = (-((level-0) * (level-0)) + 140)/(140);
        int furNum = (int) (rand.nextInt(20) * furTech);

        double foodTech = (-((level-1) * (level-1)) + 140)/(140);
        int foodNum = (int) (rand.nextInt(20) * foodTech);

        double oreTech = (-((level-3) * (level-3)) + 140)/(140);
        int oreNum = (int) (rand.nextInt(20) * oreTech);

        double gamesTech = (-((level-6) * (level-6)) + 140)/(140);
        int gamesNum = (int) (rand.nextInt(20) * gamesTech);

        double firearmsTech = (-((level-5) * (level-5)) + 140)/(140);
        int firearmsNum = (int) (rand.nextInt(20) * firearmsTech);

        double medicineTech = (-((level-6) * (level-6)) + 140)/(140);
        int medicineNum = (int) (rand.nextInt(20) * medicineTech);

        double machinesTech = (-((level-5) * (level-5)) + 140)/(140);
        int machinesNum = (int) (rand.nextInt(20) * machinesTech);

        double narcoticsTech = (-((level-5) * (level-5)) + 140)/(140);
        int narcoticsNum = (int) (rand.nextInt(20) * narcoticsTech);

        double robotsTech = (-((level-7) * (level-7)) + 140)/(140);
        int robotsNum = (int) (rand.nextInt(20) * robotsTech);


        if (levelInt < 6) {
            robotsNum = 0;
        }
        if (levelInt < 5) {
            narcoticsNum = 0;
        }
        if (levelInt < 4) {
            machinesNum = 0;
            medicineNum = 0;
        }
        if (levelInt < 3) {
            firearmsNum = 0;
            gamesNum = 0;
        }
        if (levelInt < 2) {
            oreNum = 0;
        }
        if (levelInt < 1) {
            foodNum = 0;
        }



        HashMap<TradeGood, Integer> quantity = new HashMap<>();

        quantity.put(water, waterNum);
        quantity.put(furs, furNum);
        quantity.put(ore, oreNum);
        quantity.put(food, foodNum);
        quantity.put(games, gamesNum);
        quantity.put(firearms, firearmsNum);
        quantity.put(medicine, medicineNum);
        quantity.put(narcotics, narcoticsNum);
        quantity.put(robots, robotsNum);
        quantity.put(machines, machinesNum);


        int IEChance = rand.nextInt(100);
        if (IEChance < 15) {
            IE = true;
        } else {
            IE = false;
        }
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

    public HashMap getResourceCount() {
        return quantity;
    }

    public boolean getIE() {
        return IE;
    }
}
