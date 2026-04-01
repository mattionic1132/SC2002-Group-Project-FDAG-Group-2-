package com.combat.effects;

import com.combat.model.Combatant;

public class DefendBuff implements StatusEffect {

    private int duration = 2;
    private static final int DEFENSE_BONUS = 10;
    private boolean applied = false;

    @Override
    public void apply(Combatant target) {
        // TODO: cast target to Combatant and call target.setDefense(target.getDefense() + DEFENSE_BONUS)
        // Calling Combatant.setDefense(), Combatant.getDefense() from com.combat.model
        applied = true;
    }

    @Override
    public void remove(Combatant target) {
        target.setDefense(target.getDefense() - DEFENSE_BONUS);
    }

    @Override
    public void tick() {
        duration--;
        // TODO: when expired, reverse the defense bonus on target
        // Calling Combatant.setDefense() from com.combat.model
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    public int getDuration() {
        return duration;
    }

    @Override public boolean preventsAction() { return false; }
}
