package com.combat.effects;

// Calling Combatant from com.combat.model - replace Object with Combatant when M1 finalises
public class StunEffect implements StatusEffect {

    private int duration;

    public StunEffect(int duration) {
        this.duration = duration;
    }

    @Override
    public void apply(Object target) {
        // TODO: cast target to Combatant and call target.setStunned(true)
        // Calling Combatant.setStunned() from com.combat.model
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
