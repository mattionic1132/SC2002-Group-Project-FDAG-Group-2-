package com.combat.engine;

import com.combat.model.Enemy;
import com.combat.model.Goblin;
import com.combat.model.Wolf;
import java.util.ArrayList;
import java.util.List;

// Calling Enemy class from package com.combat.model
public class Level {

    private Difficulty difficulty;

    // Calling Enemy class from package com.combat.model
    private List<Enemy> initialSpawn;
    private List<Enemy> backupSpawn;
    private boolean backupTriggered;

    public Level(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.backupTriggered = false;
        this.initialSpawn = new ArrayList<>();
        this.backupSpawn = new ArrayList<>();

        // create spawn lists based on chosen difficulty (method below will call difficulty class)
        setupSpawns();
    }

    // sets up enemy spawn lists per assignment spec
    private void setupSpawns() {
        if (difficulty == Difficulty.EASY) {
            // Easy: 3 Goblins, no backup wave
            initialSpawn.add(new Goblin("Goblin A"));
            initialSpawn.add(new Goblin("Goblin B"));
            initialSpawn.add(new Goblin("Goblin C"));

        } else if (difficulty == Difficulty.MEDIUM) {
            // Medium: 1 Goblin + 1 Wolf initial, backup: 2 Wolves
            initialSpawn.add(new Goblin("Goblin"));
            initialSpawn.add(new Wolf("Wolf"));
            backupSpawn.add(new Wolf("Wolf A"));
            backupSpawn.add(new Wolf("Wolf B"));

        } else if (difficulty == Difficulty.HARD) {
            // Hard: 2 Goblins initial, backup: 1 Goblin + 2 Wolves
            initialSpawn.add(new Goblin("Goblin A"));
            initialSpawn.add(new Goblin("Goblin B"));
            backupSpawn.add(new Goblin("Goblin C"));
            backupSpawn.add(new Wolf("Wolf A"));
            backupSpawn.add(new Wolf("Wolf B"));
        }
    }

    // returns the initial wave of enemies for this level
    public List<Enemy> getInitialEnemies() {
        return initialSpawn;
    }

    // returns the backup wave of enemies for this level
    public List<Enemy> getBackupEnemies() {
        return backupSpawn;
    }

    // returns true if all initial enemies are defeated and backup has not yet spawned
    public boolean shouldTriggerBackup() {
        // no backup available or already triggered
        if (backupTriggered || backupSpawn.isEmpty()) return false;

        // check if any initial enemy is still alive
        for (Enemy enemy : initialSpawn) {
            if (enemy.isAlive()) return false;
        }

        // all initial enemies defeated and backup not yet triggered
        return true;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isBackupTriggered() {
        return backupTriggered;
    }

    public void setBackupTriggered(boolean backupTriggered) {
        this.backupTriggered = backupTriggered;
    }
}