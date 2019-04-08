package com.example.spacetrader.model;

import java.util.Random;

public class GameState {

    public enum Difficulty {
        BEGINNER, EASY, NORMAL, HARD, IMPOSSIBLE
    }

    public Random rng;
    private Universe universe;
    private Player player;
    private final Difficulty currentDifficulty;

    private static GameState sharedGameState;

    private GameState(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
        this.rng = new Random(); // maybe we'll add the ability to specify a seed later.
    }

    public static GameState generateGame(String playerName, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill, Difficulty difficulty) {
        sharedGameState = new GameState(difficulty);
        sharedGameState.universe = new Universe();
        sharedGameState.player = new Player(playerName, pilotSkill, fighterSkill, traderSkill, engineerSkill);
        return sharedGameState;
    }

    public static GameState getState() {
        return sharedGameState;
    }

    public Universe getUniverse() {
        return universe;
    }

    public Player getPlayer() {
        return player;
    }

    public Difficulty getDifficulty() {
        return currentDifficulty;
    }

}
