package com.combat.actions;

import com.combat.model.Combatant;
import com.combat.effects.ArcaneBuff;
import java.util.List;

public class ArcaneBlast extends SpecialSkill {

    @Override
    public void execute(Combatant source, List<Combatant> targets) {
        if (targets == null || targets.isEmpty()) return;

        for (Combatant target : targets) {
            int damage = Math.max(0, source.getAttack() - target.getDefense());
            target.takeDamage(damage);

            if (!target.isAlive()) {
                source.addStatusEffect(new ArcaneBuff());
            }
        }
        resetCooldown();
    }
}
}
