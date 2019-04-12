package com.example.spacetrader.model.event;

import android.util.Log;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.event.Event;

/**
 * Police stop your ship and may ask to search it or arrest you (if a criminal)
 */
public class PoliceEvent extends Event {
    /**
     * The police event
     */
    public PoliceEvent() {

    }

    @Override
    public void run() {
        name = "police officer";
        Log.d("Random Event", "you ran a police event");
        this.description = "You encounter police. They want to search your ship. I hope you don't have any illegal goods!";
    }

    @Override
    public void attack() {
        Log.d("Random Event", "you attacked the police");
        int difficulty = GameState.getState().rng.nextInt(12) + 1;
        if (GameState.getState().getPlayer().getFighter() >= difficulty) {
            consequence = "You defeated the police!. A local resistance group takes notice, and thanks you for your help in " +
                    "liberating the galaxy with a reward of 200 credits!";
            GameState.getState().getPlayer().addCredits(200);
        } else {
            int creds = GameState.getState().getPlayer().getCredits();
            creds = creds/4;
            consequence = "You fight hard, but the police defeat you. They seize all any illegal goods you might have and" +
                    "go through your wallet. You lose " + creds + "credits";
            GameState.getState().getPlayer().removeCredits(creds);
            int narcs = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(TradeGood.NARCOTICS);
            int guns = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(TradeGood.FIREARMS);
            GameState.getState().getPlayer().getShip().removeFromInventory(TradeGood.NARCOTICS, narcs);
            GameState.getState().getPlayer().getShip().removeFromInventory(TradeGood.FIREARMS, guns);
        }
    }

    @Override
    public void flee() {
        Log.d("Random Event", "you ran from the police");
        int difficulty = GameState.getState().rng.nextInt(12) + 1;
        if (GameState.getState().getPlayer().getFighter() >= difficulty) {
            consequence = "Nice work there Lightning McQueen! You're outta there before the police can even radio for backup!";
        } else if (GameState.getState().getPlayer().getFighter() >= difficulty/2) {
            consequence = "You put the pedal to the metal and barely escape with your life. Your ship takes a few hits in the process." +
                    "Repairing that's gonna run you around 25 credits";
            GameState.getState().getPlayer().removeCredits(25);
        } else {
            int creds = GameState.getState().getPlayer().getCredits();
            creds = creds/8;
            GameState.getState().getPlayer().removeCredits(creds);
            consequence = "You try to flee, but you're clearly never seen Tokyo Drift. The police overtake you and seize your illegal goods, as well as going " +
                    "through your pockets a bit, (about " + creds + " credits), to pay for the trouble of catching you.";
            int narcs = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(TradeGood.NARCOTICS);
            int guns = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(TradeGood.FIREARMS);
            GameState.getState().getPlayer().getShip().removeFromInventory(TradeGood.NARCOTICS, narcs);
            GameState.getState().getPlayer().getShip().removeFromInventory(TradeGood.FIREARMS, guns);



        }
    }

    @Override
    public void talk() {
        Log.d("Random Event", "you let the police search your ship");
        int narcs = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(TradeGood.NARCOTICS);
        int guns = GameState.getState().getPlayer().getShip().getQuantityOfTradeGood(TradeGood.FIREARMS);
        GameState.getState().getPlayer().getShip().removeFromInventory(TradeGood.NARCOTICS, narcs);
        GameState.getState().getPlayer().getShip().removeFromInventory(TradeGood.FIREARMS, guns);
        int fine = 0;
        if (narcs > 0) {
            if (guns > 0) {
                fine = 200;
            } else {
                fine = 100;
            }
        } else {
            if (guns>0) {
                fine = 100;
            }
        }
        if (fine > 0) {
            consequence = "Transporting illegal goods is in violation of Galactic Law 63-4A. Your illegal goods are confiscated. The " +
                    "corrupt officers take " + fine + " credits from you and decide not to bring you to space prison. Count your blessings. ";
            GameState.getState().getPlayer().removeCredits(fine);
        } else {
            consequence = "The police find no illegal goods on your ship. You continue on your way";
        }
    }
}
