package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;

public class PlayerInfoViewModel extends ViewModel {

    public String getPlayerName() {
        return GameState.getState().getPlayer().getName();
    }

    public String getShipName() {
        return GameState.getState().getPlayer().getShip().toString();
    }

    public int getPlayerCash() {
        return GameState.getState().getPlayer().getCredits();
    }

    public int getShipFuel() {
        return GameState.getState().getPlayer().getShip().getCurrentFuel();
    }

    public int getMaxShipFuel() {
        return GameState.getState().getPlayer().getShip().getMaxFuel();
    }

    public void maxOutFuel() {
        GameState.getState().getPlayer().getShip().setCurrentFuel(GameState.getState().getPlayer().getShip().getMaxFuel());
    }

    public void add1Fuel() {
        GameState.getState().getPlayer().getShip().addFuel(1);
    }

}
