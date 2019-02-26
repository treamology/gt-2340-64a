package com.example.spacetrader.model;

import java.util.Random;

public class GameState {

    public enum Difficulty {
        BEGINNER, EASY, NORMAL, HARD, IMPOSSIBLE;
    }

    public Random rng;
    private Universe universe;
    private Player player;
    private Difficulty currentDifficulty;

    private static GameState sharedGameState;

    private GameState(Player player, Difficulty difficulty) {
        this.player = player;
        this.currentDifficulty = difficulty;
        this.rng = new Random(); // maybe we'll add the ability to specify a seed later.
    }

    public static GameState generateGame(Player player, Difficulty difficulty) {
        sharedGameState = new GameState(player, difficulty);
        sharedGameState.universe = new Universe();
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
