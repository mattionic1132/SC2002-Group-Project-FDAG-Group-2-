package com.combat.effects;

// Calling Combatant from com.combat.model - replace Object with Combatant when M1 finalises
public class SmokeBombEffect implements StatusEffect {

    private int duration = 2;

    @Override
    public void apply(Object target) {
        // TODO: flag on Combatant that incoming enemy damage = 0
        // Calling Combatant.setSmokeBombActive(true) from com.combat.model
        // BattleEngine checks this flag before applying enemy damage
    }

    @Override
    public void tick() {
        duration--;
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    public int getDuration() {
        return duration;
    }
}
