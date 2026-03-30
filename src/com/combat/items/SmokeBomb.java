package com.combat.items;

import com.combat.engine.BattleEngine;
import com.combat.model.Combatant;

public class SmokeBomb implements Item {

    private String name;
    private boolean used;

    public SmokeBomb(String name) {
        this.name = name;
        this.used = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUsed() {
        return used;
    }

    @Override
    public void use(Combatant source, BattleEngine context) {
        if (used) {
            System.out.println(name + " has already been used!");
            return;
        }
        System.out.println(source.getName() + " used " + name + "!");
        used = true;
    }
}
