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

    //Initializes a player and a game state
    private GameState game;
    private Player player;

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
        GameState game = new GameState(player, difficulty);
        Log.d("APP",String.format("Player name: %s\n Pilot: %d\n Fighter: %d\n Trader: %d\n Engineer: %d\n Difficulty: %s",
                playerName, pilotSkill, fighterSkill, traderSkill, engineerSkill, difficulty.toString()));
    }

    //Increases or decreases Engineer Skill by int EngineerSkill
    //Seems more practical to increase instead of set to a certain value so you
    //don't have to calculate what it will be when you call it
    public void updateEngineer(int engineerSkill) {
        player.setEngineer(engineerSkill + player.getEngineer());
    }

    //Increases or decreases Trader Skill by int traderSkill
    public void updateTrader(int traderSkill) {
        player.setTrader(traderSkill + player.getTrader());
    }

    //Increases or decreases Pilot Skill by int pilotSkill
    public void updatePilot(int pilotSkill) {
        player.setPilot(pilotSkill + player.getPilot());
    }

    //Increases or decreases Fighter Skill by int fighterSkill
    public void updateFighter(int fighterSkill) {
        player.setFighter(fighterSkill + player.getFighter());
    }

    //Sets the ship
    public void setShip(Ship ship) {
        player.setShip(ship);
    }

    //Increases or decreases credits by int creds
    public void updateCredits(int creds) {
        player.setCredits(creds + player.getCredits());

    }

    //Getters
    public String getName() { return player.getName(); }
    public int getPilot() {return player.getPilot(); }
    public int getFighter() {return player.getFighter(); }
    public int getTrader() {return player.getTrader(); }
    public int getEngineer() {return player.getEngineer(); }
    public Ship getShip() {return player.getShip();}



}
