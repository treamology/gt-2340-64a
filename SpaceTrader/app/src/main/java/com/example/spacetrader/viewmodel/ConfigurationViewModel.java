package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.spacetrader.model.GameState;

public class ConfigurationViewModel extends AndroidViewModel {
    public ConfigurationViewModel(@NonNull Application app) {
        super(app);
    }

    public void createGame(String playerName,
                           int pilotSkill,
                           int fighterSkill,
                           int traderSkill,
                           int engineerSkill,
                           GameState.Difficulty difficulty) {
        // to be implemented
        Log.d("APP",String.format("Player name: %s\n Pilot: %d\n Fighter: %d\n Trader: %d\n Engineer: %d\n Difficulty: %s",
                playerName, pilotSkill, fighterSkill, traderSkill, engineerSkill, difficulty.toString()));
    }
}
