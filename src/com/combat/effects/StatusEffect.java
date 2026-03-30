package com.combat.effects;

public interface StatusEffect {
    void apply(Object target); // target is Combatant - replace Object when M1 finalises
    void tick();
    boolean isExpired();
}

