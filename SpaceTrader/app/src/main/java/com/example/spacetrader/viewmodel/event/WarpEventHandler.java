package com.example.spacetrader.viewmodel.event;

import com.example.spacetrader.viewmodel.modeldisplay.DisplayedSolarSystem;

public interface WarpEventHandler {
    void onWarp(int newSystemIndex);
}
