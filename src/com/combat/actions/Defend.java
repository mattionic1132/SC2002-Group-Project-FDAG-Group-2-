package com.combat.actions;

// import combatant package
// import StatusEffects

public class Defend implements Action {

    @Override
    public void execute(Combatant source, Combatant[] targets) {
        // Apply defense buff that lasts 2 rounds
        // this defendBuff is a class implementing the StatusEffect interface
        // is DefendBuff static? i'll assume it is for now

        // if DefendBuff has been used then there will be a cooldown
        // if not used / cooldown over, then proceed
        if (DefendBuff.isExpired()){
            System.out.println("Defend is on cooldown");
        }
        else DefendBuff.apply(source);
    }
}
