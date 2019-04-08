package com.example.spacetrader.model.event;

import android.util.Log;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.TradeGood;

import java.util.LinkedHashMap;

/**
 * Pirates demand some of your trade goods or they attack
 */
public class PirateEvent extends Event {

    TradeGood demands;
    int quantity;

    public PirateEvent() {}

    @Override
    public void run() {
        demands = null;
        name = "pirate";
        TradeGood chosen;
        if (GameState.getState().getPlayer().getShip().getNumOpenCargoBays() <
                GameState.getState().getPlayer().getShip().getTotalCargoBays()) {
            while (demands == null) {
                chosen = TradeGood.getRandom();
                int numInInventory = GameState.getState().getPlayer().getShip(
                ).getQuantityOfTradeGood(chosen);
                if (numInInventory > 0) {
                    demands = chosen;
                    quantity = numInInventory;
                    description = "You are beset by pirates! They demand all of your "
                            + chosen.getName() + " for safe passage!";
                }
            }
        } else {
            description = "You are beset by pirates!"
                    + " They demand any fuel you don't need for safe passage!";
        }
        //Log.d("Random Event", "you ran a pirate event");
    }

    @Override
    public void attack() {
        int difficulty = GameState.getState().rng.nextInt(15) + 1;
        if (GameState.getState().getPlayer().getFighter() >= difficulty) {
            if (GameState.getState().getPlayer().getShip().getNumOpenCargoBays() > 0) {
                TradeGood reward = TradeGood.getRandom();
                GameState.getState().getPlayer().getShip().addToInventory(reward, 1);
                consequence = "You defeated the pirates! You looted 1 " + reward.getName()
                    + " from their ship.";
            } else {
                GameState.getState().getPlayer().addCredits(100);
                consequence = "You defeated the pirates!"
                    + " Your cargo bays are full so you looted 100 credits from them.";
            }
        } else {
            GameState.getState().getPlayer().getShip().loseInventory();
            GameState.getState().getPlayer().getShip().subtractFuel(
                    GameState.getState().getPlayer().getShip().getCurrentFuel());
            consequence = "You were not a good enough fighter to beat the pirates."
                + " Since you resisted, they took all of your trade goods and unneeded fuel.";
        }
        //Log.d("Random Event", "you fought the pirate");
    }

    @Override
    public void flee() {
        int difficulty = GameState.getState().rng.nextInt(13) + 1;
        if (GameState.getState().getPlayer().getPilot() >= difficulty) {
            consequence = "You successfully escaped the pirates!";
        } else {
            GameState.getState().getPlayer().getShip().loseInventory();
            GameState.getState().getPlayer().getShip().subtractFuel(
                    GameState.getState().getPlayer().getShip().getCurrentFuel());
            consequence = "You were not a good enough pilot to escape the pirates."
                    + " Since you resisted, they took all of your trade goods and unneeded fuel";
        }
        //Log.d("Random Event", "you tried to escape the pirate");
    }

    @Override
    public void talk() {
        if (demands == null) {
            GameState.getState().getPlayer().getShip().subtractFuel(
                    GameState.getState().getPlayer().getShip().getCurrentFuel());
        } else {
            GameState.getState().getPlayer().getShip().removeFromInventory(demands, quantity);
        }
        consequence = "You complied with the pirates' demands.";
        //Log.d("Random Event", "you gave in to the pirate's demands");
    }
}
