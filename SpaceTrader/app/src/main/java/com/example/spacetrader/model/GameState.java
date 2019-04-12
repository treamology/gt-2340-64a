package com.example.spacetrader.model;

import java.util.Random;
/**
 * Class that initializes the game state, creates the game
 */
public class GameState {

    public enum Difficulty {
        BEGINNER, EASY, NORMAL, HARD, IMPOSSIBLE
    }

    public Random rng;
    private Universe universe;
    private Player player;
    private final Difficulty currentDifficulty;

    private static GameState sharedGameState;
    /**
     * Sets the difficulty level of the game
     * @param difficulty the difficulty level of the game
     */
    private GameState(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
        this.rng = new Random(); // maybe we'll add the ability to specify a seed later.
    }
    /**
     * Initializes the game, setting the attributes of the player and the game
     * @param playerName the name of the player
     * @param pilotSkill the number of skill points in pilot
     * @param fighterSkill the number of skill points in fighter
     * @param traderSkill the number of skill points in trader
     * @param engineerSkill the number of skill points in engineer
     * @param difficulty the difficulty setting of the game
     * @return GameState the GameState initialized with all the necessary information
     */
    public static GameState generateGame(String playerName, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill, Difficulty difficulty) {
        sharedGameState = new GameState(difficulty);
        sharedGameState.universe = new Universe();
        sharedGameState.player = new Player(playerName, pilotSkill, fighterSkill, traderSkill, engineerSkill);
        return sharedGameState;
    }
    /**
     * Returns the game state
     * @return the current GameState
     */
    public static GameState getState() {
        return sharedGameState;
    }
    /**
     * Returns the universe
     * @return the current universe
     */
    public Universe getUniverse() {
        return universe;
    }
    /**
     * Returns the player
     * @return the current player
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * Returns the difficulty
     * @return the difficulty level
     */
    public Difficulty getDifficulty() {
        return currentDifficulty;
    }

}
