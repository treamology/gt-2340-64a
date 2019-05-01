package com.example.spacetrader.viewmodel;

import java.util.List;

public interface ISpaceMapViewModel {
    List<DisplayedSolarSystem> getSystems();
    List<DisplayedSolarSystem> getSystems(int boundsX, int boundsY);
    SolarSystemInfo getSystemInfo(int index);
    int getSystemDistanceFromPlayer(SolarSystemInfo info);
    int getCurrentShipFuel();
}
