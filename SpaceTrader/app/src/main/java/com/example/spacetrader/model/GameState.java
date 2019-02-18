package com.example.spacetrader.model;

public class GameState {

    public enum Difficulty {
        BEGINNER, EASY, NORMAL, HARD, IMPOSSIBLE;
    }

    public Player player;
    public Difficulty currentDifficulty;

    public GameState(Player player, Difficulty difficulty) {
        this.player = player;
        this.currentDifficulty = difficulty;
    }

}
