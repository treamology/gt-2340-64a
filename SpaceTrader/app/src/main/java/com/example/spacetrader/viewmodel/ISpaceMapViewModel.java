package com.example.spacetrader.viewmodel;

import java.util.List;
/**
 * SpaceMapViewModel interface
 */
public interface ISpaceMapViewModel {

    /**
     * Gets the system
     * @return a list of the systems
     */
    List<DisplayedSolarSystem> getSystems();

    /**
     * Gets the systems in a limit
     * @param boundsX the x limit to get systems in
     * @param boundsY  the y limit to get systems in
     * @return a list of system in the bounds
     */
    List<DisplayedSolarSystem> getSystems(int boundsX, int boundsY);

    /**
     * Gets the info of a system at index
     * @param index  the index of the system
     * @return the system info
     */
    SolarSystemInfo getSystemInfo(int index);

    /**
     * Gets the distance from the player
     * @param info the info of the system
     * @return the distance from the player
     */
    int getSystemDistanceFromPlayer(SolarSystemInfo info);

    /**
     * Gets the ship's current fuel
     * @return the ship's current fuel
     */
    int getCurrentShipFuel();
}
