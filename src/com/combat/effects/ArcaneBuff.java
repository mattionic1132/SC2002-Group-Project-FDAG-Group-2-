package com.combat.effects;

// Calling Combatant from com.combat.model - replace Object with Combatant when M1 finalises
// Calling Wizard from com.combat.model
public class ArcaneBuff implements StatusEffect {

    private static final int ATTACK_BONUS = 10;
    // ArcaneBuff lasts until end of level - never expires naturally
    private boolean expired = false;

    @Override
    public void apply(Object target) {
        // TODO: cast target to Combatant and call target.setAttack(target.getAttack() + ATTACK_BONUS)
        // Calling Combatant.setAttack(), Combatant.getAttack() from com.combat.model
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
}
