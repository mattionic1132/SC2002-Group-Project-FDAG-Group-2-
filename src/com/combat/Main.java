package com.combat;

import com.combat.cli.GameCLI;
import com.combat.cli.GameSettings;
import com.combat.model.Enemy;
import com.combat.model.Player;
import com.combat.model.Warrior;
import com.combat.model.Wizard;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create GameCLI instance
        GameCLI cli = new GameCLI();

        // call showLoadingScreen() to get player selections
        GameSettings settings = cli.showLoadingScreen();

        // create player based on characterType
        Player player;
        if (settings.getCharacterType() == 1) {
            player = new Warrior("Warrior");
            player.setSpecialSkill(new ShieldBash());
        } else {
            player = new Wizard("Wizard");
            player.setSpecialSkill(new ArcaneBlast());
        }

        // loop through itemIndices and add items to player
        for (int index : settings.getItemIndices()) {
            Item item;
            if (index == 1) {
                item = new Potion();
            } else if (index == 2) {
                item = new PowerStone();
            } else if (index == 3) {
                item = new SmokeBomb();
            } else {
                continue;
            }
            player.addItem(item);
        }

        // create Level with chosen difficulty
        Level level = new Level(settings.getDifficulty());

        // get initial enemies from level
        List<Enemy> enemies = level.getEnemies();

        // create BattleEngine and start battle
        BattleEngine engine = new BattleEngine(player, enemies, level, cli);
        engine.startBattle();
    }
