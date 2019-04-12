package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.GameState;

/**
 * Class for the Configuration ViewModel
 */
public class ConfigurationViewModel extends AndroidViewModel {

    /**
     * Initializes the configuration ViewModel
     * @param application the application to use
     */
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Creates the game and the player
     * @param playerName the name of the player
     * @param pilotSkill the pilot skill level
     * @param fighterSkill the fighter skill level
     * @param traderSkill the trader skill level
     * @param engineerSkill the engineer skill level
     * @param difficulty the difficulty level of the game
     */
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
