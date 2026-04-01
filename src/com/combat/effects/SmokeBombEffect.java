package com.combat.effects;

import com.combat.model.Combatant;

// Calling Combatant from com.combat.model - replace Object with Combatant when M1 finalises
public class SmokeBombEffect implements StatusEffect {

    private int duration = 2;

    @Override
    public void apply(Combatant target) {
        target.setSmokeBombActive(true);
    }

    @Override
    public void remove(Combatant target) {
        target.setSmokeBombActive(false);
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

    @Override public boolean preventsAction() { return true; }
}
