package com.example.spacetrader.model;

import java.util.Random;

public class GameState {

    public static Random rng;

    public enum Difficulty {
        BEGINNER, EASY, NORMAL, HARD, IMPOSSIBLE;
    }

    public Player player;
    public Difficulty currentDifficulty;

    public GameState(Player player, Difficulty difficulty) {
        this.player = player;
        this.currentDifficulty = difficulty;
        rng = new Random(); // maybe we'll add the ability to specify a seed later.
    }

}
