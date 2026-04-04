package com.combat.engine;

import com.combat.model.Enemy;
import com.combat.model.Player;
import java.util.List;

// Calling Player class from package com.combat.model
// Calling Enemy class from package com.combat.model
// Calling Combatant class from package com.combat.model
// Calling Action interface from package com.combat.actions
// Calling StatusEffect interface from package com.combat.effects
// Calling GameCLI class from package com.combat.cli
public class BattleEngine {

    // Calling Player class from package com.combat.model
    private Player player;              // Player

    // Calling Enemy class from package com.combat.model
    private List<Enemy> enemies;            // List<Enemy>

    // Calling TurnOrderStrategy interface from package com.combat.engine
    private TurnOrderStrategy turnOrder;

    private Level level;
    private int roundCount;

    public BattleEngine(Player player, List<Enemy> enemies, Level level) {
        this.player = player;
        this.enemies = enemies;
        this.level = level;
        this.turnOrder = new SpeedBasedOrder();
        this.roundCount = 0;
    }

    /**
     * Entry point called by GameCLI to start the battle loop.
     * Calling GameCLI class from package com.combat.ui
     */
    public void startBattle() {
        // TODO: loop calling processRound() until checkBattleEnd() is true
    }

    /**
     * Processes a single round of combat.
     * Order of operations:
     * 1. Increment roundCount
     * 2. Check backup spawn via Level
     * 3. Determine turn order via TurnOrderStrategy
     * 4. For each combatant: apply status effects, check stun, execute action
     * 5. Check battle end after each action
     * 6. Display round summary via GameCLI
     *
     * Calling TurnOrderStrategy from package com.combat.engine
     * Calling Combatant.applyStatusEffects() from package com.combat.model
     * Calling Action.execute() from package com.combat.actions
     * Calling StatusEffect.tick() from package com.combat.effects
     * Calling GameCLI.showRoundSummary() from package com.combat.ui
     */
    public void processRound() {
        // TODO: implement round loop
        roundCount++;
    }

    /**
     * Checks if the battle has ended.
     * Win condition: all enemies defeated and no backup remaining
     * Lose condition: player hp reaches 0
     *
     * Calling Player.isAlive() from package com.combat.model
     * Calling Enemy.isAlive() from package com.combat.model
     * Calling GameCLI.showVictoryScreen() from package com.combat.ui
     * Calling GameCLI.showDefeatScreen() from package com.combat.ui
     *
     * @return true if battle is over
     */
    public boolean checkBattleEnd() {
        // TODO: check player alive and all enemies defeated
        return false;
    }

    /**
     * Spawns the backup wave of enemies mid-battle.
     * Called when initial wave is fully defeated.
     *
     * Calling Level.getBackupEnemies() from package com.combat.engine
     * Calling GameCLI.showBattleStatus() from package com.combat.ui
     */
    public void triggerBackupSpawn() {
        // TODO: add backup enemies to active enemies list
        // set level.setBackupTriggered(true)
    }

    public int getRoundCount() {
        return roundCount;
    }
}