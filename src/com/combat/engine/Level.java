package com.combat.engine;

import java.util.List;

// Calling Enemy class from package com.combat.model
public class Level {

    private Difficulty difficulty;

    // Calling Enemy class from package com.combat.model
    private List<?> initialSpawn;   // List<Enemy>
    private List<?> backupSpawn;    // List<Enemy>
    private boolean backupTriggered;

    public Level(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.backupTriggered = false;
    }

    /**
     * Returns the initial wave of enemies for this level.
     * Calling Enemy class from package com.combat.model
     */
    public List<?> getInitialEnemies() {
        // TODO: return initialSpawn list based on difficulty
        return initialSpawn;
    }

    /**
     * Returns the backup wave of enemies for this level.
     * Calling Enemy class from package com.combat.model
     */
    public List<?> getBackupEnemies() {
        // TODO: return backupSpawn list based on difficulty
        return backupSpawn;
    }

    /**
     * Returns true if all initial enemies are defeated
     * and backup has not yet been triggered.
     */
    public boolean shouldTriggerBackup() {
        // TODO: check if initial wave cleared and backup not yet spawned
        return false;
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