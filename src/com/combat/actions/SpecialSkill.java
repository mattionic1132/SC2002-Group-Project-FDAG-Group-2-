package com.combat.actions;

public class SpecialSkill implements Action {

    @Override
    public void execute(Combatant source, Combatant[] targets){

        // shieldbash
        if (source instanceof Warrior){
            shieldBash(targets);
        }
        // arcaneblast
        else if (source instanceof Wizard) {
            arcaneBlast(source, targets);

        }
    }

    public void shieldBash(Combatant[] targets){
        // placeholder for now, since player can choose which entity they want to target
        // can change once we bring the code tgt
        Combatant target = targets.get(0);

        // stun effect is a class implementing StatusEffect interface
        target.applyStatusEffect(new StunEffect(2));
    }

    public void arcaneBlast(Combatant source, Combatant[] targets){
        for (Combatant target : targets) {

            int damage = Math.max(0, source.getAttack() - target.getDefense());

            target.takeDamage(damage);

            // Check if enemy is defeated AFTER damage
            // any enemy defeated by arcane blast will increase the wizard's attack by 10 permanently
            if (!target.isAlive()) {
                source.setHp(source.getHp() + 10); // permanent for level
            }
        }
    }
}
