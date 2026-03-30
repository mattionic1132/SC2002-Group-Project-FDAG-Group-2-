package com.combat.items;

import com.combat.engine.BattleEngine;
import com.combat.model.Combatant;

public interface Item {
    String getName();

    boolean isUsed();

    void use(Combatant source, BattleEngine context);
}
