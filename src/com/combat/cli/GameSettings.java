package com.combat.cli;

import com.combat.engine.Difficulty;

public class GameSettings {
    private final int characterType; // 1: Warrior, 2: Wizard
    private final int[] itemIndices;  // Choice of 2 items
    private final Difficulty difficulty;    // 1: Easy, 2: Medium, 3: Hard

    public GameSettings(int characterType, int[] itemIndices, Difficulty difficulty) {
        this.characterType = characterType;
        this.itemIndices = itemIndices;
        this.difficulty = difficulty;
    }

    // Getters only (Immutable data)
    public int getCharacterType() { return characterType; }
    public int[] getItemIndices() { return itemIndices; }
    public Difficulty getDifficulty() { return difficulty; }
}
