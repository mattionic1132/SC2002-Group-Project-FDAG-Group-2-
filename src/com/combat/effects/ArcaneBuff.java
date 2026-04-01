package com.combat.effects;

import com.combat.model.Combatant;

// Calling Combatant from com.combat.model - replace Object with Combatant when M1 finalises
// Calling Wizard from com.combat.model
public class ArcaneBuff implements StatusEffect {

    private static final int ATTACK_BONUS = 10;
    // ArcaneBuff lasts until end of level - never expires naturally
    private boolean expired = false;

    @Override
    public void apply(Combatant target) {
        target.setAttack(target.getAttack() + ATTACK_BONUS);

    }

    @Override
    public void remove(Combatant target) {
        //to fill
    }

    @Override
    public void tick() {
        // ArcaneBuff does not tick down - lasts until end of level
        // BattleEngine calls expire() when level ends
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    // Called by BattleEngine at end of level
    public void expire() {
        expired = true;
    }

    // added kill-bonus method
    public void addKillBonus(Combatant target) {
        target.setAttack(target.getAttack() + ATTACK_BONUS);
    }

    @Override public boolean preventsAction() { return false; }
}
