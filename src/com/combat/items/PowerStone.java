package com.combat.items;

import com.combat.engine.BattleEngine;
import com.combat.model.Combatant;
import com.combat.model.Player;

public class PowerStone implements Item {

    private String name;
    private boolean used;

    public PowerStone(String name) {
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
        //implement powerstone effect: can use skill wo cooldown
        ((Player) source).getSpecialSkill().execute(source, context.getAliveEnemies());
        System.out.println(source.getName() + " used " + name + "!");
        used = true;
    }
}
