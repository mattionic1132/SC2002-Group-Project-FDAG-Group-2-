package com.combat.items;

import com.combat.engine.BattleEngine;
import com.combat.model.Combatant;

import java.util.List;

public class Potion implements Item {

    private String name;
    private boolean used;

    public Potion(String name) {
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
    public void use(Combatant source, List<Combatant> targets) {
        if (used) {
            System.out.println(name + " has already been used!");
            return;
        }
        // TODO: Implement potion effect (e.g., restore HP to source)
        System.out.println(source.getName() + " used " + name + "!");
        source.setHp(source.getHp() + 100);
        used = true;
    }
}