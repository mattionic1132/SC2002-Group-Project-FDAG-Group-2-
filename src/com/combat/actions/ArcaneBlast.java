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
            // The Kill Bonus: If the enemy dies, permanently add +10 ATK
            if (!target.isAlive()) {
                System.out.println(target.getName() + " was defeated! Wizard gains +10 Attack!");

                // We directly grab the current attack and add 10 to it!
                source.setAttack(source.getAttack() + 10);
            }
        }
        resetCooldown();
    }
        // implement abstract method for Action
        @Override
        public String getOutcome(Combatant source, List<Combatant> targets) {
            StringBuilder result = new StringBuilder(": ArcaneBlast hit all enemies");
            for (Combatant target : targets) {
                result.append(" | ").append(target.getName())
                        .append(" HP: ").append(target.getHp());
                if (!target.isAlive()) result.append(" ELIMINATED");
            }
            return result.toString();
        }

    }
