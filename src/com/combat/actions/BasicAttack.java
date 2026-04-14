package com.combat.actions;

import com.combat.model.Combatant;
import java.util.List;

public class BasicAttack implements Action {

    @Override
    public void execute(Combatant source, List<Combatant> targets) {
        if (targets == null || targets.isEmpty()) return;

        // BasicAttack hits one target
        Combatant target = targets.get(0);
        //when enemies attack, checks if smokebomb active
        if (target.isSmokeBombActive()){
            System.out.println(source.getName() + " attacks but smoke bomb blocks all damage!");
            return; //no damage will be dealt to player
        }

        // damage formula: max(0, attacker attack - target defense)
        int damage = Math.max(0, source.getAttack() - target.getDefense());
        target.takeDamage(damage);
    }

    // added the implementation for getting outcome upon basic attack
    @Override
    public String getOutcome(Combatant source, List<Combatant> targets) {
        Combatant target = targets.get(0);

        //outcome for smoke bomb
        if (target.isSmokeBombActive()) {
            return ": 0 damage (Smoke Bomb active)";
        }

        int damage = Math.max(0, source.getAttack() - target.getDefense());
        String result = ": HP " + (target.getHp() + damage) + " -> " + target.getHp() + " (dmg: " + damage + ")";
        if (!target.isAlive()){
            result += " | " + target.getName() + " ELIMINATED";
            return result;
        }
        return result;
    }
}