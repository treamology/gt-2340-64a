package com.example.spacetrader;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.system.Position;
import com.example.spacetrader.model.system.PriceIncreaseEvent;
import com.example.spacetrader.model.system.ResourceBias;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.model.system.TechLevel;

import org.junit.Test;

import static org.junit.Assert.*;

public class getPriceUnitTest {

    Player p1 = new Player("testPlayer",16,0,0,0);

    GameState game = GameState.generateGame(p1, GameState.Difficulty.BEGINNER);

    Position ps1 = new Position(100, 200);
    Position ps2 = new Position(150, 250);
    Position ps3 = new Position(250, 300);

    @Test(timeout = 200000)
    public void getPriceTechLevelTest() {

        SolarSystem planet = new SolarSystem("testPlanet1", ps1, TechLevel.AGRICULTURAL, ResourceBias.NOSPECIALRESOURCES);
        SolarSystem betterPlanet = new SolarSystem("testPlanet2", ps2, TechLevel.RENAISSANCE, ResourceBias.NOSPECIALRESOURCES);
        SolarSystem bestPlanet = new SolarSystem("testPlanet3",ps3,TechLevel.HITECH,ResourceBias.NOSPECIALRESOURCES);

        planet.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);
        bestPlanet.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);
        betterPlanet.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);

        //testing that higher tech levels have higher priced goods
        assertTrue("Prices of goods do not increase with techLevel", TradeGood.WATER.getPrice(planet) < TradeGood.WATER.getPrice(betterPlanet));
        assertTrue("Prices of goods do not increase with techLevel", TradeGood.WATER.getPrice(betterPlanet) < TradeGood.WATER.getPrice(bestPlanet));
    }

    @Test(timeout = 200)
    public void getPriceCRTest() {
        SolarSystem waterPlanet = new SolarSystem("testPlanet1", ps1, TechLevel.AGRICULTURAL, ResourceBias.LOTSOFWATER);
        SolarSystem dryPlanet = new SolarSystem("testPlanet2", ps2, TechLevel.AGRICULTURAL, ResourceBias.NOSPECIALRESOURCES);

        waterPlanet.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);
        dryPlanet.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);

        assertTrue("CR event doesn't reduce price of good.",TradeGood.WATER.getPrice(waterPlanet) < TradeGood.WATER.getPrice(dryPlanet));
    }

    @Test(timeout = 200)
    public void getPriceERTest() {
        SolarSystem dryPlanet = new SolarSystem("testPlanet1", ps1, TechLevel.AGRICULTURAL, ResourceBias.DESERT);
        SolarSystem planet = new SolarSystem("testPlanet2", ps2, TechLevel.AGRICULTURAL, ResourceBias.NOSPECIALRESOURCES);

        dryPlanet.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);
        planet.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);

        assertTrue("ER event doesn't increase price of good.",TradeGood.WATER.getPrice(planet) < TradeGood.WATER.getPrice(dryPlanet));
    }

    @Test(timeout = 200)
    public void getPriceIETest() {
        SolarSystem planet1 = new SolarSystem("testPlanet1", ps1, TechLevel.AGRICULTURAL, ResourceBias.NOSPECIALRESOURCES);
        SolarSystem planet2 = new SolarSystem("testPlanet2", ps2, TechLevel.AGRICULTURAL, ResourceBias.NOSPECIALRESOURCES);

        planet1.setCurrentIncreaseEvent(PriceIncreaseEvent.NONE);
        planet2.setCurrentIncreaseEvent(PriceIncreaseEvent.DROUGHT);

        assertTrue("IE event doesn't increase price of good.",TradeGood.WATER.getPrice(planet1) < TradeGood.WATER.getPrice(planet2));
    }
}