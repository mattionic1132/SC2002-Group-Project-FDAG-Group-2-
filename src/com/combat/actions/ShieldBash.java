package com.combat.actions;

import com.combat.model.Combatant;
import com.combat.effects.StunEffect;
import java.util.List;

public class ShieldBash extends SpecialSkill {

    @Override
    public void execute(Combatant source, List<Combatant> targets) {
        if (targets == null || targets.isEmpty()) return;

        Combatant target = targets.get(0);
        int damage = Math.max(0, source.getAttack() - target.getDefense());
        target.takeDamage(damage);
        target.addStatusEffect(new StunEffect(2));
    }

    // added the getting outcome for shiled bash
    @Override
    public String getOutcome(Combatant source, List<Combatant> targets) {
        Combatant target = targets.get(0);
        String result = ": " + target.getName() + " STUNNED for 2 turns";
        if (!target.isAlive()) {
            result += " | ELIMINATED";
            return result;
        }
        return result;
    }
}
