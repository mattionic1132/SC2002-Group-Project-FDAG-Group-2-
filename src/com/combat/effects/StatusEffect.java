package com.combat.effects;

import com.combat.model.Combatant;

public interface StatusEffect {
    void apply(Combatant target); // target is Combatant
    void tick();
    void remove(Combatant target);
    boolean isExpired();
}

