package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.GameState;


public class ConfigurationViewModel extends AndroidViewModel {

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    //Creates the game and the player
    public void createGame(String playerName,
                           int pilotSkill,
                           int fighterSkill,
                           int traderSkill,
                           int engineerSkill,
                           GameState.Difficulty difficulty) {

        //Sets game attributes
        GameState game = GameState.generateGame(playerName, pilotSkill, fighterSkill, traderSkill, engineerSkill, difficulty);
        Player player = game.getPlayer();

        Log.d("APP",String.format("Player name: %s\n Pilot: %d\n Fighter: %d\n Trader: %d\n Engineer: %d\n Difficulty: %s",
                player.getName(), player.getPilot(), player.getFighter(),
                player.getTrader(), player.getEngineer(), game.getDifficulty().toString()));
    }
}
