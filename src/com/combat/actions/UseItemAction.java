package com.combat.actions;

import com.combat.model.Combatant;
import com.combat.items.Item;
import com.combat.engine.BattleEngine;
import java.util.List;

public class UseItemAction implements Action {

    private Item item;
    private BattleEngine context;

    public UseItemAction(Item item, BattleEngine context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public void execute(Combatant source, List<Combatant> targets){
        item.use(source, context);
    }
    // added the get puctome implementation needed from interface
    @Override
    public String getOutcome(Combatant source, List<Combatant> targets) {
        return ":" + item.getName() + " used";
    }
}
