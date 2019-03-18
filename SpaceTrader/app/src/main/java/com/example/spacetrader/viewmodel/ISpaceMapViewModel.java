package com.example.spacetrader.viewmodel;

import com.example.spacetrader.viewmodel.modeldisplay.DisplayedSolarSystem;

import java.util.List;

public interface ISpaceMapViewModel {
    List<DisplayedSolarSystem> getSystems();
    List<DisplayedSolarSystem> getSystems(int boundsX, int boundsY);
    int getCurrentShipFuel();
}
