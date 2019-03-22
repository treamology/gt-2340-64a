package com.example.spacetrader.model.system;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.TradeGood;

import java.util.LinkedHashMap;
import java.util.Map;
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

    // Constant state through the whole game
    String name;
    Position position;
    TechLevel techLevel;
    ResourceBias resourceBias;
    int imageIndex;

    // State that changes each turn
    LinkedHashMap<TradeGood, Integer> quantities;
    LinkedHashMap<TradeGood, Integer> prices;
    PriceIncreaseEvent currentIncreaseEvent;
    boolean visited;

    public SolarSystem(String name, Position position, TechLevel techLevel, ResourceBias resourceBias) {
        this.name = name;
        this.position = position;
        this.techLevel = techLevel;
        this.resourceBias = resourceBias;
        this.visited = false;
        this.quantities = new LinkedHashMap<>();
        this.prices = new LinkedHashMap<>();

        Random rng = GameState.getState().rng;
        this.imageIndex = rng.nextInt(10) + 1;

        generateNewTradeGoods();
    }

    public void generateNewTradeGoods() {
        TradeGood water = TradeGood.WATER;
        TradeGood furs = TradeGood.FURS;
        TradeGood food = TradeGood.FOOD;
        TradeGood ore = TradeGood.ORE;
        TradeGood games = TradeGood.GAMES;
        TradeGood firearms = TradeGood.FIREARMS;
        TradeGood medicine = TradeGood.MEDICINE;
        TradeGood machines = TradeGood.MACHINES;
        TradeGood narcotics = TradeGood.NARCOTICS;
        TradeGood robots = TradeGood.ROBOTS;

        Random rand = GameState.getState().rng;

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

        quantities.put(water, waterNum);
        quantities.put(furs, furNum);
        quantities.put(ore, oreNum);
        quantities.put(food, foodNum);
        quantities.put(games, gamesNum);
        quantities.put(firearms, firearmsNum);
        quantities.put(medicine, medicineNum);
        quantities.put(narcotics, narcoticsNum);
        quantities.put(robots, robotsNum);
        quantities.put(machines, machinesNum);

        int IEChance = rand.nextInt(100);
        if (IEChance < 15) {
            currentIncreaseEvent = PriceIncreaseEvent.values()[rand.nextInt(PriceIncreaseEvent.values().length - 2) + 1];
        } else {
            currentIncreaseEvent = PriceIncreaseEvent.NONE;
        }

        int waterPrice = water.getPrice(this);
        int fursPrice = furs.getPrice(this);
        int orePrice = ore.getPrice(this);
        int foodPrice = food.getPrice(this);
        int gamesPrice = games.getPrice(this);
        int firearmsPrice = firearms.getPrice(this);
        int medicinePrice = medicine.getPrice(this);
        int narcoticsPrice = narcotics.getPrice(this);
        int robotsPrice = robots.getPrice(this);
        int machinesPrice = machines.getPrice(this);

        prices.put(water, waterPrice);
        prices.put(furs, fursPrice);
        prices.put(ore, orePrice);
        prices.put(food, foodPrice);
        prices.put(games, gamesPrice);
        prices.put(firearms, firearmsPrice);
        prices.put(medicine, medicinePrice);
        prices.put(narcotics, narcoticsPrice);
        prices.put(robots, robotsPrice);
        prices.put(machines, machinesPrice);
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

    public Map<TradeGood, Integer> getResourceCount() {
        return quantities;
    }
    public int getImageIndex() {
        return imageIndex;
    }

    public LinkedHashMap<TradeGood, Integer> getQuantities() {
        return quantities;
    }

    public LinkedHashMap<TradeGood, Integer> getPrices() {
        return prices;
    }

    public boolean decreaseQuantity(TradeGood good, int quantity) {
        if (quantities.get(good) - quantity >= 0) {
            quantities.put(good, quantities.get(good) - quantity);
            return true;
        }
        return false;
    }

    public void increaseQuantity(TradeGood good, int quantity) {
        quantities.put(good, quantities.get(good) + quantity);
    }

    public PriceIncreaseEvent getCurrentIncreaseEvent() {
        return currentIncreaseEvent;
    }
    public int getDistanceFromPlayer() {
        return position.getManhattanDistanceTo(GameState.getState().getPlayer().getCurrentSystem().getPosition());
    }
}
