package com.combat.engine;

import com.combat.actions.Action;
import com.combat.actions.BasicAttack;
import com.combat.actions.Defend;
import com.combat.actions.SpecialSkill;
import com.combat.actions.UseItemAction;
import com.combat.cli.GameCLI;
import com.combat.items.Item;
import com.combat.model.Combatant;
import com.combat.model.Enemy;
import com.combat.model.Player;

import java.util.ArrayList;
import java.util.List;

// NOTE: firsy draft of Battle Engine but need to figure out whether:
// restructure player and special Skill
// check for error and do main after


public class BattleEngine {

    // Calling Player class from package com.combat.model
    private Player player;

    // Calling Enemy class from package com.combat.model
    private List<Enemy> enemies;

    // Calling TurnOrderStrategy interface from package com.combat.engine
    private TurnOrderStrategy turnOrder;

    // Calling GameCLI class from package com.combat.cli
    private GameCLI cli;

    private Level level;
    private int roundCount;

    public BattleEngine(Player player, List<Enemy> enemies, Level level, GameCLI cli) {
        this.player = player;
        this.enemies = enemies;
        this.level = level;
        this.cli = cli;
        this.turnOrder = new SpeedBasedOrder();
        this.roundCount = 0;
    }

    //Entry point called by GameCLI to start the battle loop.
    //Calling GameCLI class from package com.combat.cli
    public void startBattle() {
        System.out.println("\nBattle Start!\n");

        while (!checkBattleEnd()) {
            processRound();
        }

        // show appropriate end screen based on outcome
        if (player.isAlive()) {
            cli.showVictoryScreen(player, roundCount);
        } else {
            int remaining = 0;
            for (Enemy e : enemies) {
                if (e.isAlive()) remaining++;
            }
            cli.showDefeatScreen(remaining, roundCount);
        }
    }

    // Rough layout og process below
     // Processes a single round of combat.
     // Order of operations:
     // 1. Increment roundCount
     // 2. Check backup spawn via Level
     // 3. Determine turn order via TurnOrderStrategy
     // 4. For each combatant: apply status effects, check stun, execute action
     // 5. Check battle end after each action
     // 6. Display round summary via GameCLI


    public void processRound() {
        // fix no counter
        roundCount++;
        System.out.println("\n======== ROUND " + roundCount + " ========");

        // check if backup wave should spawn at start of round
        if (level.shouldTriggerBackup()) {
            triggerBackupSpawn();
        }

        // build combatant list for this round (player + all alive enemies)
        List<Combatant> allCombatants = new ArrayList<>();
        allCombatants.add(player);
        for (Enemy e : enemies) {
            if (e.isAlive()) allCombatants.add(e);
        }

        // sort by speed descending via TurnOrderStrategy
        List<Combatant> orderedCombatants = turnOrder.determineOrder(allCombatants);

        // each combatant takes their turn in order
        for (Combatant combatant : orderedCombatants) {

            // skip if eliminated mid-round
            if (!combatant.isAlive()) continue;

            // apply status effects at start of each turn
            combatant.applyStatusEffects();

            // skip turn if stunned or otherwise prevented from acting
            // fix logic wrong from group review
            if (!combatant.canAct()) {
                cli.showCombatantAction(combatant, "");
                continue;
            }

            // player turn - get action choice from CLI
            if (combatant instanceof Player) {
                List<Combatant> aliveEnemies = getAliveEnemies();
                List<Integer> choices = cli.promptPlayerAction(combatant, aliveEnemies);
                handlePlayerAction((Player) combatant, choices, aliveEnemies);

            } else {
                // enemy turn - always basic attack on player
                handleEnemyAction((Enemy) combatant);
            }

            // check if battle ended after every single action
            if (checkBattleEnd()) return;
        }

        // display end of round summary
        int potionCount = countItem("Potion");
        int smokeBombCount = countItem("Smoke Bomb"); //must make sure follow exactly as in Main
        cli.showRoundSummary(roundCount, allCombatants, false, player.getSpecialSkill(), potionCount, smokeBombCount);
    }

    //Checks if the battle has ended.
     //Win condition: all enemies defeated and no backup remaining
    //Lose condition: player hp reaches 0
    public boolean checkBattleEnd() {
        // lose condition - player defeated
        if (!player.isAlive()) return true;

        // check if any enemy is still alivw
        for (Enemy e : enemies) {
            if (e.isAlive()) return false;
        }

        // all enemies dead - check if backup can still spawn
        if (level.shouldTriggerBackup()) return false;

        // all enemies dead, no backup remaining - player wins
        return true;
    }


     //Spawns the backup wave of enemies mid-battle.
     //Called when initial wave is fully defeated.
    public void triggerBackupSpawn() {
        List<Enemy> backup = level.getBackupEnemies();
        enemies.addAll(backup);
        level.setBackupTriggered(true);
        System.out.println("\n*** BACKUP ENEMIES HAVE ARRIVED! ***");
        for (Enemy e : backup) {
            System.out.println("  -> " + e.getName() + " entered the battle!");
        }
    }

    // translates player menu choice into an action and executes it
    private void handlePlayerAction(Player player, List<Integer> choices, List<Combatant> aliveEnemies) {
        int actionChoice = choices.get(0);
        int targetIndex = choices.get(1);

        Action action = null;
        List<Combatant> targets = new ArrayList<>();

        if (actionChoice == 1) {
            // basic attack - hits selected enemy target
            action = new BasicAttack();
            targets.add(aliveEnemies.get(targetIndex));

        } else if (actionChoice == 2) {
            // defend - no target needed, applies DefendBuff to self
            action = new Defend();

        } else if (actionChoice == 3) {
            // use item - find first unused item in inventory
            for (Item item : player.getItems()) {
                if (!item.isUsed()) {
                    action = new UseItemAction(item, this);
                    cli.showCombatantAction(player, item, "");
                    break;
                }
            }
            // fix no = sign
            if (action == null) {
                System.out.println("No items available!");
                return;
            }

        } else if (actionChoice == 4) {
            // special skill - retrieve from player and check cooldown
            SpecialSkill skill = player.getSpecialSkill();
            if (skill == null) {
                System.out.println("No special skill assigned!");
                return;
            }
            if (!skill.isReady()) {
                System.out.println("Special skill on cooldown! (" + skill.getCooldown() + " rounds remaining)");
                return;
            }
            // shield bash needs a single target, arcane blast hits all enemies
            if (targetIndex >= 0) {
                targets.add(aliveEnemies.get(targetIndex));
            } else {
                targets.addAll(aliveEnemies);
            }
            action = skill;
        }

        if (action != null) {
            action.execute(player, targets);

            // get outcome from action itself each action describes what happened (after shafi push)
            String outcome = action.getOutcome(player, targets);
            Combatant target = targets.isEmpty() ? null : targets.get(0);

            // matt added condition check --> items and defend affect the player only (no enemy target)
            // showCombatantAction(subject, target, action, outcome) requires a target
            // so if target is null, print action result directly to avoid NullPointerException
            if (target == null) {
                System.out.println(player.getName() + " -> " + action.getClass().getSimpleName() + outcome);
            }

            else {
                cli.showCombatantAction(player, target, action, outcome);
            }

            // check if any target was eliminated after action
            for (Combatant t : targets) {
                if (!t.isAlive()) cli.showCombatantAction(t);
            }
        }
    }

    // enemy always performs basic attack on player
    private void handleEnemyAction(Enemy enemy) {
        // smoke bomb check - if active, enemy attack deals 0 damage
        if (player.isSmokeBombActive()) {
            System.out.println(enemy.getName() + " attacks but smoke bomb blocks all damage!");
            return;
        }

        List<Combatant> targets = new ArrayList<>();
        targets.add(player);
        Action attack = new BasicAttack();
        attack.execute(enemy, targets);

        // get outcome from action itself (after shafi push)
        String outcome = attack.getOutcome(enemy, targets);
        cli.showCombatantAction(enemy, player, attack, outcome);
    }

    // returns list of enemies still alive this round
    private List<Combatant> getAliveEnemies() {
        List<Combatant> alive = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.isAlive()) alive.add(e);
        }
        return alive;
    }

    // counts unused items of a given name in player inventory
    private int countItem(String itemName) {
        int count = 0;
        for (Item item : player.getItems()) {
            if (!item.isUsed() && item.getName().equalsIgnoreCase(itemName)) {
                count++;
            }
        }
        return count;
    }

    public int getRoundCount() {
        return roundCount;
    }
}