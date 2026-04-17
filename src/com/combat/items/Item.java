package com.combat.items;

import com.combat.engine.BattleEngine;
import com.combat.model.Combatant;

import java.util.List;

public interface Item {
    String getName();

    boolean isUsed();

    // replaced BattleEngine context with List<Combatant> Targets to respect DIP
    void use(Combatant source, List<Combatant> targets);


}
