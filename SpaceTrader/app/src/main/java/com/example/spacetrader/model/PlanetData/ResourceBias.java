package com.example.spacetrader.model.PlanetData;
import java.util.Random;

public abstract class ResourceBias {
    String type;

    @Override
    public String toString() {
        return type;
    }

    /**
     * Randomly generates a resource bias for a planet
     * @return the resource bias
     */
    public static ResourceBias Generate() {
        Random rng = new Random();
        switch (rng.nextInt(24)) {
            case 0:
                return new MineralRich();
            case 1:
                return new MineralPoor();
            case 2:
                return new Desert();
            case 3:
                return new LotsOfWater();
            case 4:
                return new RichSoil();
            case 5:
                return new PoorSoil();
            case 6:
                return new RichFauna();
            case 7:
                return new Lifeless();
            case 8:
                return new WeirdMushrooms();
            case 9:
                return new LotsOfHerbs();
            case 10:
                return new Artistic();
            case 11:
                return new Warlike();
            default:
                return new NoSpecialResources();
        }
    }
}
