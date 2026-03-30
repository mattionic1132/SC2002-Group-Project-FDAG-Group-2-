package com.combat.actions;

import com.combat.model.Combatant;
import com.combat.effects.DefendBuff;
import java.util.List;

public class Defend implements Action {

    @Override
    public void execute(Combatant source, List<Combatant> targets) {
        // Defend applies a DefendBuff to the source (the player who chose to defend)
        // +10 defense for current round and next round (2 turns)
        // Calling DefendBuff from com.combat.effects
        source.addStatusEffect(new DefendBuff());
    }
}
