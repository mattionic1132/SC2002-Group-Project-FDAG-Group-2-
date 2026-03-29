package com.combat.actions;

// import combatant package

public class BasicAttack implements Action {

    private Combatant[] targets;

    public BasicAttack(Combatant[] targets) {
        this.targets = targets;
    }

    @Override
    // calculates the damage done to the targets
    // i use array to take into account multiple targets being chosen by player, still works with only 1 target
    public void execute(Combatant source, Combatant[] targets) {
        for (Combatant target : targets) {
            int damage = Math.max(0, source.getAttack() - target.getDefense());
            target.takeDamage(damage);
        }
    }
}
