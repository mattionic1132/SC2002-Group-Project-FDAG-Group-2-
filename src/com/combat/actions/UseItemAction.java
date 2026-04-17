package com.combat.actions;

import com.combat.model.Combatant;
import com.combat.items.Item;
import com.combat.engine.BattleEngine;
import java.util.List;

public class UseItemAction implements Action {

    private Item item;
    private List<Combatant> aliveEnemies;

    public UseItemAction(Item item, List<Combatant> aliveEnemies) {
        this.item = item;
        this.aliveEnemies = aliveEnemies;
    }

    @Override
    public void execute(Combatant source, List<Combatant> targets){
        item.use(source, aliveEnemies);
    }
    // added the get puctome implementation needed from interface
    @Override
    public String getOutcome(Combatant source, List<Combatant> targets) {
        return ":" + item.getName() + " used";
    }
}
