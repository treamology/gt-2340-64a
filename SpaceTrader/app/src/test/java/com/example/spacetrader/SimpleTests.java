package com.example.spacetrader;
import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.Ship;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.Universe;
import com.example.spacetrader.model.system.SolarSystem;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleTests {
    @Test
    public void skillPointTest() {
        Player pl = new Player("", 4, 4, 4, 4);
        assertEquals(16, pl.skillPointSum());
    }

    @Test
    public void removeCreditsTest() {
        GameState testState = GameState.generateGame("Player", 1, 1, 1, 1, GameState.Difficulty.BEGINNER);
        Player pl = testState.getPlayer();
        assertEquals(true, pl.removeCredits(99));
        assertEquals(false, pl.removeCredits(20000));
    }

    @Test
    public void addCredTest() {
        GameState testState = GameState.generateGame("Player", 1, 1, 1, 1, GameState.Difficulty.BEGINNER);
        Player p1 = testState.getPlayer();
        p1.addCredits(9);
        assertEquals(10009, p1.getCredits());
    }

    @Test
    public void SolarSystemTest() {
        GameState testState = GameState.generateGame("Player", 1, 1, 1, 1, GameState.Difficulty.BEGINNER);
        Universe testUniverse = testState.getUniverse();
        List<SolarSystem> testSystems = testUniverse.getSystems();
        SolarSystem sr = testSystems.get(1);
        LinkedHashMap<TradeGood, Integer> resourceMap = sr.getQuantities();
        TradeGood good = null;
        for (TradeGood tr : resourceMap.keySet()) {
            if (tr != null) {
                good = tr;
            }
        }
        int temp = sr.getInventoryQuantity(good);
        sr.increaseQuantity(good, 1);
        assertEquals(temp, sr.getInventoryQuantity(good));
    }

    @Test
    public void SolarSystemTest2() {
        GameState testState = GameState.generateGame("Player", 1, 1, 1, 1, GameState.Difficulty.BEGINNER);
        Universe testUniverse = testState.getUniverse();
        List<SolarSystem> testSystems = testUniverse.getSystems();
        SolarSystem sr = testSystems.get(1);
        LinkedHashMap<TradeGood, Integer> resourceMap = sr.getQuantities();
        TradeGood good = null;
        for (TradeGood tr : resourceMap.keySet()) {
            good = tr;
            break;
        }
        assertEquals(false, sr.decreaseQuantity(good, 1));
        assertEquals(false, sr.decreaseQuantity(good, 1000000));
    }

    @Test
    public void travelTest() {
        GameState testState = GameState.generateGame("Player", 1, 1, 1, 1, GameState.Difficulty.HARD);
        Universe testUniverse = testState.getUniverse();
        List<SolarSystem> testSystems = testUniverse.getSystems();
        SolarSystem sr = testSystems.get(1);
        Player pl = new Player("", 4, 4, 4, 4);
        SolarSystem oldNull = pl.getCurrentSystem();
        pl.travelToSystem(1);
        assertFalse(oldNull == null);
        assertTrue(pl.getCurrentSystemIndex() == 1);
        sr.generateGoodsAndPrices();

        Player p2 = new Player("", 2, 6, 4, 4);
        SolarSystem old = p2.getCurrentSystem();
        old.setVisited(true);
        p2.getShip().subtractFuel(old.getPosition().getEuclideanDistanceTo(sr.getPosition()));
        p2.travelToSystem(2);
        assertTrue(p2.getCurrentSystemIndex() == 2);
        sr.generateGoodsAndPrices();

        p2.getShip().addFuel(old.getPosition().getEuclideanDistanceTo(sr.getPosition()));
    }

}
