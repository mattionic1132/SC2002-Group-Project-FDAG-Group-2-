package com.combat.actions;

import com.combat.model.Combatant;
import java.util.List;

public abstract class SpecialSkill implements Action {

    private int cooldown = 0;
    private static final int COOLDOWN_TURNS = 3;

    public boolean isReady() {
        return cooldown == 0;
    }

    public void decrementCooldown() {
        if (cooldown > 0) cooldown--;
    }

    public void resetCooldown() {
        cooldown = COOLDOWN_TURNS;
    }

    public int getCooldown() {
        return cooldown;
    }

    // abstract - forces ShieldBash and ArcaneBlast to implement it
    @Override
    public abstract void execute(Combatant source, List<Combatant> targets);
}
