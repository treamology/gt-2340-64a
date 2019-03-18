package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.viewmodel.modeldisplay.DisplayedSolarSystem;

import java.util.ArrayList;
import java.util.List;

public class UniverseViewModel extends ViewModel implements ISpaceMapViewModel {

    @Override
    public List<DisplayedSolarSystem> getSystems() {
        List<SolarSystem> systems = GameState.getState().getUniverse().getSystems();
        List<DisplayedSolarSystem> reps = new ArrayList<>();
        Player player = GameState.getState().getPlayer();

        for (int i = 0; i < systems.size(); i++) {
            SolarSystem system = systems.get(i);
            SolarSystem playerSystem = player.getCurrentSystem();
            DisplayedSolarSystem rep = new DisplayedSolarSystem(
                    system.getPosition().getX(),
                    system.getPosition().getY(),
                    system.getName(),
                    system.getVisited(),
                    system.getPosition().equals(playerSystem.getPosition()),
                    system.getImageIndex());
            reps.add(rep);
        }
        return reps;
    }

    @Override
    public List<DisplayedSolarSystem> getSystems(int boundsX, int boundsY) {
        List<SolarSystem> systems = GameState.getState().getUniverse().getSystems();
        List<DisplayedSolarSystem> reps = new ArrayList<>();
        Player player = GameState.getState().getPlayer();
        for (int i = 0; i < systems.size(); i++) {
            SolarSystem system = systems.get(i);
            SolarSystem playerSystem = player.getCurrentSystem();
            if (system.getPosition().getX() > playerSystem.getPosition().getX() - boundsX / 2
                && system.getPosition().getX() < playerSystem.getPosition().getX() + boundsX / 2
                && system.getPosition().getY() > playerSystem.getPosition().getY() - boundsY / 2
                && system.getPosition().getY() < playerSystem.getPosition().getY() + boundsY / 2) {
                DisplayedSolarSystem rep = new DisplayedSolarSystem(
                        system.getPosition().getX(),
                        system.getPosition().getY(),
                        system.getName(),
                        system.getVisited(),
                        system.getPosition().equals(playerSystem.getPosition()),
                        system.getImageIndex());
                reps.add(rep);
            }
        }
        return reps;
    }

    @Override
    public int getCurrentShipFuel() {
        return GameState.getState().getPlayer().getShip().getCurrentFuel();
    }
}
