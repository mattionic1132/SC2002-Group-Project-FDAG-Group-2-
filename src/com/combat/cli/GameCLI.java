package com.combat.cli;

import com.combat.actions.Action;
import com.combat.actions.SpecialSkill;
import com.combat.engine.Difficulty;
import com.combat.items.Item;
import com.combat.model.Enemy;
import com.combat.model.Combatant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameCLI {
    private final Scanner scanner;

    public GameCLI() {
        this.scanner = new Scanner(System.in);
    }



    // Initiation Sequence for the game, displays options
    public GameSettings showLoadingScreen() {
        System.out.println("========================================");
        System.out.println("   TURN-BASED COMBAT ARENA: INITIATION  ");
        System.out.println("========================================\n");

        // picking character
        System.out.println("[STEP 1: CHOOSE YOUR HERO]");
        System.out.println("1. Warrior (HP: 260, ATK: 40, DEF: 20, SPD: 30)");
        System.out.println("   Skill: Shield Bash (Stun target for 2 turns)");
        System.out.println("2. Wizard  (HP: 200, ATK: 50, DEF: 10, SPD: 20)");
        System.out.println("   Skill: Arcane Blast (AoE damage, +10 ATK per kill)");
        System.out.print("Selection (1,2): ");
        int charChoice = scanner.nextInt();

        // picking items
        System.out.println("\n[STEP 2: SELECT TWO ITEMS]");
        System.out.println("1. Potion (Heal 100 HP)");
        System.out.println("2. Power Stone (Free Special Skill use)");
        System.out.println("3. Smoke Bomb (0 incoming damage for 2 turns)");

        int[] itemChoices = new int[2];
        for (int i = 0; i < 2; i++) {
            System.out.print("Select Item " + (i + 1) + " (1,2,3): ");
            itemChoices[i] = scanner.nextInt();
        }

        // selecting Difficulty
        System.out.println("\n[STEP 3: SELECT DIFFICULTY]");
        System.out.println("1. Easy   (Initial: 3 Goblins)");
        System.out.println("2. Medium (Initial: 1 Goblin, 1 Wolf | Backup: 2 Wolves)");
        System.out.println("3. Hard   (Initial: 2 Goblins | Backup: 1 Goblin, 2 Wolves)");
        System.out.print("Selection: ");
        int choice = scanner.nextInt();
        Difficulty difficulty = switch (choice) {
            case 1:
                yield Difficulty.EASY;
            case 2:
                yield Difficulty.MEDIUM;
            case 3:
                yield Difficulty.HARD;
            default:
                System.out.println("Invalid choice. Defaulting to EASY.");
                yield Difficulty.EASY;
        };
        scanner.nextLine();

        System.out.println("\nLoading Battle... Good luck!\n");

        // Return a helper object containing all player selections for the game
        return new GameSettings(charChoice, itemChoices, difficulty);
    }



    /**
     * Displays action performed in turn (Method Overloading Used!!)
     * Use cases respectively:
     * If Combatant.isStunned()
     * If Combatant eliminated this round
     * If Player uses item
     * If Combatant uses attack
     */
    public void showCombatantAction(Combatant subject, String turnOutcome) {
        System.out.println(subject.getName() + " -> Stunned: Turn Skipped" + turnOutcome);
    }

    public void showCombatantAction(Combatant subject) {
        System.out.println(subject.getName() + " -> Eliminated this Round");
    }

    public void showCombatantAction(Combatant subject, Item item, String turnOutcome) {
        System.out.println(subject.getName() + " -> Item -> " + item.getClass().getSimpleName() + " Used" + turnOutcome);
    }

    public void showCombatantAction(Combatant subject, Combatant target, Action action, String turnOutcome) {
        System.out.println(subject.getName() + " -> " + action.getClass().getSimpleName() + " -> " + target.getName() + turnOutcome);
    }
    //TODO: turnOutcome logic (target HP change, StatusEffects updates)



    //Input for Player Choices
    public List<Integer> promptPlayerAction(Combatant subject, List<Combatant> enemies) {
        List<Integer> selections = new ArrayList<>();

        // Display Action Menu
        System.out.println("\n== ITS YOUR TURN! WHAT WILL YOU DO? ==");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend (+10 Defense)");
        System.out.println("3. Use Item");
        System.out.println("4. Special Skill");
        System.out.print("Select an action (1-4): ");

        int actionChoice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        selections.add(actionChoice);

        // Selecting Target if Required
        // Basic Attack (1) and Shield Bash (Warrior Special - 4) require a target
        if (actionChoice == 1 || (actionChoice == 4 && subject.getName().equals("Warrior"))) {
            System.out.println("\nSelect a target:");
            for (int i = 0; i < enemies.size(); i++) {
                Combatant e = enemies.get(i);
                System.out.println((i + 1) + ". " + e.getName() + " (HP: " + e.getHp() + ")");
            }
            System.out.print("Target choice: ");
            int targetIndex = scanner.nextInt() - 1; // Convert to 0-based index
            scanner.nextLine();
            selections.add(targetIndex);
        } else {
            // No target needed for Defend (2), Items (3), or Wizard's Special Skill (4)
            selections.add(-1);
        }
        //returns [action choice, target choice or -1] as integers
        return selections;

    }



    public void showRoundSummary(int roundNumber, List<Combatant> players, boolean gameIsOver, SpecialSkill skill) {
        /**
         * Displays the end-of-round status bar and prepares the player for the next round.
         * Corresponds to Game Flow Examples [cite: 256-257, 294-295].
         */
        // Build the Combatant HP string
        StringBuilder playerStatus = new StringBuilder();
        for (Combatant p : players) {
            playerStatus.append(String.format(" | %s HP: %d/%d", p.getName(), p.getHp(), p.getMaxHp()));
            if (!p.canAct()){
                playerStatus.append(" [STUNNED]");
            }
        }

        // Format the round statistics line
        System.out.printf("End of Round %d: %s HP: %d/%d%s | Potion: %d | Smoke Bomb: %d | Special Skills Cooldown: %d rounds%n",
                roundNumber,
                playerStatus.toString(),
                players.getItemCount("Potion"),
                players.getItemCount("Smoke Bomb"),
                skill.getCooldown() //TODO: SpecialSkill can be used with Player class
        );

        System.out.println("--------------------------------------------------------------------------------");

        // Title for the next round if the game continues
        if (!gameIsOver) {
            System.out.println("\nRound " + (roundNumber + 1));
        }
    }



    // Victory Screen With Some Basic Statistics
    public void showVictoryScreen(Combatant player, int roundNumber) {
        System.out.println("\n==================================================");
        System.out.println("   CONGRATULATIONS! YOU HAVE DEFEATED ALL ENEMIES  ");
        System.out.println("==================================================");
        System.out.println("Statistics:");
        System.out.println("-> Remaining HP: " + player.getHp()); // [cite: 56]
        System.out.println("-> Total Rounds: " + roundNumber); // [cite: 56]
        System.out.println("==================================================");
    }



    // Defeat Screen With Some Basic Statistics
    public void showDefeatScreen(int enemiesRemaining, int roundNumber) {
        System.out.println("\n==================================================");
        System.out.println("   DEFEATED. Don't give up, try again!            ");
        System.out.println("==================================================");
        System.out.println("Statistics:");
        System.out.println("-> Enemies remaining: " + enemiesRemaining); // [cite: 59]
        System.out.println("-> Total Rounds Survived: " + roundNumber); // [cite: 59]
        System.out.println("\n==================================================");
    }
}

