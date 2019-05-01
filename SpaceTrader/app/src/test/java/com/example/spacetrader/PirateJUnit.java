package com.example.spacetrader;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.event.PirateEvent;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PirateJUnit {


    @Test
    public void testAll() {
        GameState.generateGame("", 0, 16, 0, 0, GameState.Difficulty.BEGINNER);
        GameState.getState().rng = new Random(100);
        PirateEvent event = new PirateEvent();
        event.run();
        event.attack();
        assertEquals(GameState.getState().getPlayer().getShip().getTotalCargoBays() -
                GameState.getState().getPlayer().getShip().getNumOpenCargoBays(), 1);
        GameState.getState().getPlayer().getShip().loseInventory();
        GameState.getState().getPlayer().getShip().addToInventory(TradeGood.WATER,
                GameState.getState().getPlayer().getShip().getTotalCargoBays());
        int formerCredits = GameState.getState().getPlayer().getCredits();
        event = new PirateEvent();
        event.run();
        event.attack();
        assertEquals(GameState.getState().getPlayer().getCredits(), formerCredits + 100);
        GameState.getState().getPlayer().setFighter(0);
        event = new PirateEvent();
        event.run();
        event.attack();
        event.flee();
        event.talk();
        assertEquals(GameState.getState().getPlayer().getShip().getTotalCargoBays() -
                GameState.getState().getPlayer().getShip().getNumOpenCargoBays(), 0);
        assertEquals(GameState.getState().getPlayer().getShip().getCurrentFuel(), 0);
    }
}
