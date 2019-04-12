package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
/**
 * The player info ViewModel class
 */
public class PlayerInfoViewModel extends ViewModel {

    /**
     * Gets the player's name
     * @return the player's name
     */
    public String getPlayerName() {
        return GameState.getState().getPlayer().getName();
    }

    /**
     * Gets the player's ship name
     * @return the player's ship name
     */
    public String getShipName() {
        return GameState.getState().getPlayer().getShip().toString();
    }

    /**
     * Gets the player's credits
     * @return how many credits the player has
     */
    public int getPlayerCash() {
        return GameState.getState().getPlayer().getCredits();
    }

    /**
     * Gets the player's ship's fuel
     * @return the amount of fuel in the player's ship
     */
    public int getShipFuel() {
        return GameState.getState().getPlayer().getShip().getCurrentFuel();
    }

    /**
     * Gets the max ship fuel
     * @return the max fuel that the player's ship can hold
     */
    public int getMaxShipFuel() {
        return GameState.getState().getPlayer().getShip().getMaxFuel();
    }

    /**
     * Set's the player's ship's fuel to max
     */
    public void maxOutFuel() {
        GameState.getState().getPlayer().getShip().setCurrentFuel(GameState.getState().getPlayer().getShip().getMaxFuel());
    }
    /**
     * Adds 1 fuel to the player's ship
     */
    public void add1Fuel() {
        GameState.getState().getPlayer().getShip().addFuel(1);
    }

}
