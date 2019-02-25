package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.system.SolarSystem;

public class SystemInfoViewModel extends ViewModel {

    private SolarSystem currentSystem;

    public SystemInfoViewModel() {
        super();
        this.currentSystem = GameState.getState().getPlayer().getCurrentSystem();
    }
    public String getSystemName() {
        return currentSystem.getName();
    }
    public int getSystemX() {
        return currentSystem.getPosition().getX();
    }
    public int getSystemY() {
        return currentSystem.getPosition().getY();
    }
    public String getResourceBias() {
        return currentSystem.getResourceBias().name;
    }
    public String getTechLevel() {
        return currentSystem.getTechLevel().name;
    }
}
