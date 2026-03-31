package com.combat.cli;

import com.combat.engine.Difficulty;

import java.util.Scanner;

public class GameCLI {
    private final Scanner scanner;
    public GameCLI() {
        this.scanner = new Scanner(System.in);
    }
    // Initiation Sequence for the game
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

        // 3. Select Difficulty
        System.out.println("\n[STEP 3: SELECT DIFFICULTY]");
        System.out.println("1. Easy   (Initial: 3 Goblins)");
        System.out.println("2. Medium (Initial: 1 Goblin, 1 Wolf | Backup: 2 Wolves)");
        System.out.println("3. Hard   (Initial: 2 Goblins | Backup: 1 Goblin, 2 Wolves)");
        System.out.print("Selection: ");
        int choice = scanner.nextInt();
//        Difficulty difficulty;
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
    public void showBattleStatus() {

    }
    public void promptPlayerAction() {

    }
    public void showRoundSummary() {

    }
    public void showVictoryScreen() {

    }
    public void showDefeatScreen() {

    }
}
