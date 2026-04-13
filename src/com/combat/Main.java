package com.combat;
// re added jon's main bcos merge wrong git version
// matt added main flow explanation --> main is used to:
// handles the overall flow and calls the game menu
// adds users' selections (character selection, item assignment, and level configuration)
// calls an instance of game logic before the round starts.
import com.combat.actions.ArcaneBlast;
import com.combat.actions.ShieldBash;
import com.combat.cli.GameCLI;
import com.combat.cli.GameSettings;
import com.combat.engine.BattleEngine;
import com.combat.engine.Level;
import com.combat.items.Item;
import com.combat.items.Potion;
import com.combat.items.PowerStone;
import com.combat.items.SmokeBomb;
import com.combat.model.Enemy;
import com.combat.model.Player;
import com.combat.model.Warrior;
import com.combat.model.Wizard;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create CLI and show loading screen - returns player selections
        GameCLI cli = new GameCLI();
        GameSettings settings = cli.showLoadingScreen();

        // create player based on character choice
        Player player;
        if (settings.getCharacterType() == 1) {
            player = new Warrior("Warrior");
            // assign shield bash as warrior's special skill
            player.setSpecialSkill(new ShieldBash());
        } else {
            player = new Wizard("Wizard");
            // assign arcane blast as wizard's special skill
            player.setSpecialSkill(new ArcaneBlast());
        }

        // add chosen items to player inventory
        for (int itemIndex : settings.getItemIndices()) {
            Item item;
            if (itemIndex == 1) {
                item = new Potion("Potion");
            } else if (itemIndex == 2) {
                item = new PowerStone("Power Stone");
            } else {
                item = new SmokeBomb("Smoke Bomb");
            }
            player.addItem(item);
        }

        // create level with chosen difficulty - spawns enemies automatically
        Level level = new Level(settings.getDifficulty());
        List<Enemy> enemies = level.getInitialEnemies();

        // create battle engine and start the game
        BattleEngine engine = new BattleEngine(player, enemies, level, cli);
        engine.startBattle();
    }
}