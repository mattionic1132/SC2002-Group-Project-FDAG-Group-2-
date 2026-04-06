package com.combat;

// go through main pseudo code flow with all members to make sure make sense
public class Main {
    public static void main(String[] args) {

        // create GameCLI instance

        // call showLoadingScreen() to get player selections
        // returns GameSettings object with characterType, itemIndices, difficulty

        // if characterType == 1, create Warrior
        // else create Wizard

        // assign special skill to player
        // if Warrior, setSpecialSkill(new ShieldBash())
        // if Wizard, setSpecialSkill(new ArcaneBlast())

        // loop through itemIndices from GameSettings
        // if index == 1, create Potion
        // if index == 2, create PowerStone
        // if index == 3, create SmokeBomb
        // add each item to player via addItem()

        // create Level with chosen difficulty
        // Level constructor automatically sets up spawn lists

        // get initial enemies from level

        // create BattleEngine with player, enemies, level, cli

        // call engine.startBattle()
    }
}
