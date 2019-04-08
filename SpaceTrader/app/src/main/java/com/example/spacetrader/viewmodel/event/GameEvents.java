package com.example.spacetrader.viewmodel.event;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.system.SolarSystem;

import java.util.ArrayList;
import java.util.List;

public class GameEvents {
    private static final List<WarpEventHandler> warpEvents = new ArrayList<>();

    public static void addWarpEvent(WarpEventHandler warpEventHandler) {
        warpEvents.add(warpEventHandler);
    }
    public static void warpToSystem(int index) {
        SolarSystem system = GameState.getState().getUniverse().getSystems().get(index);
        Player player = GameState.getState().getPlayer();
        player.travelToSystem(index);
        for (WarpEventHandler event : warpEvents) {
            event.onWarp();
        }
    }
}
