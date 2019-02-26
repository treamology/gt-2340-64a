package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Gnat;
import com.example.spacetrader.model.Ship;



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

        //Sets player attributes
        Player player = new Player(playerName, pilotSkill, fighterSkill, traderSkill, engineerSkill);

        //Sets game attributes
        GameState game = GameState.generateGame(player, difficulty);
        Log.d("APP",String.format("Player name: %s\n Pilot: %d\n Fighter: %d\n Trader: %d\n Engineer: %d\n Difficulty: %s",
                player.getName(), player.getPilot(), player.getFighter(),
                player.getTrader(), player.getEngineer(), game.getDifficulty().toString()));
    }
}
