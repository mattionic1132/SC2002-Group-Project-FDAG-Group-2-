package com.combat.actions;

import com.combat.model.Combatant;
import com.combat.effects.ArcaneBuff;
import java.util.List;

public class ArcaneBlast extends SpecialSkill {

    @Override
    public void execute(Combatant source, List<Combatant> targets) {
        if (targets == null || targets.isEmpty()) return;

        // create instance of Arcane buff before loop starts
        ArcaneBuff buff = new ArcaneBuff();

        for (Combatant target : targets) {
            int damage = Math.max(0, source.getAttack() - target.getDefense());
            target.takeDamage(damage);

            // when enemy killed add kill-bonus by calling method
            if (!target.isAlive()) {
                buff.addKillBonus(source);
            }
        }

        // add status effect after loop end
        source.addStatusEffect(buff);
        resetCooldown();
    }
}
