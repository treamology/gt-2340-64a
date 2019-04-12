package com.example.spacetrader.viewmodel.event;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.system.SolarSystem;

import java.util.ArrayList;
import java.util.List;
/**
 * Class for game events
 */
public class GameEvents {
    private static final List<WarpEventHandler> warpEvents = new ArrayList<>();
    /**
     * Adds a warp event
     * @param warpEventHandler the warp event handlers
     */
    public static void addWarpEvent(WarpEventHandler warpEventHandler) {
        warpEvents.add(warpEventHandler);
    }
    /**
     * Warps to a system
     * @param index the index of the system to warp to
     */
    public static void warpToSystem(int index) {
        SolarSystem system = GameState.getState().getUniverse().getSystems().get(index);
        Player player = GameState.getState().getPlayer();
        player.travelToSystem(index);
        for (WarpEventHandler event : warpEvents) {
            event.onWarp();
        }
    }
}
