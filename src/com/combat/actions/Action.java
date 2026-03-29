package com.combat.actions;

public interface Action {
    // takes in the source, and the targets, both are from combatant class
    public void execute(Combatant source, Combatant[] targets);
}
