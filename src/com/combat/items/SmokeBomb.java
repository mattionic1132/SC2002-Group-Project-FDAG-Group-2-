package com.combat.items;

import com.combat.effects.SmokeBombEffect;
import com.combat.model.Combatant;

import java.util.List;

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
    public void use(Combatant source, List<Combatant> targets) {
        if (used) {
            System.out.println(name + " has already been used!");
            return;
        }
        System.out.println(source.getName() + " used " + name + "!");
        source.addStatusEffect(new SmokeBombEffect());
        //set to true only when used, so it will be detected in enemies Basic Attack
        // before this was implemented, enemies still can attack that round even after player uses smokebomb
        // this is because applyeffects is called at the start of the round, but smokebomb used mid-round, so it wont
        //be accounted for. this is a solution to that.
        source.setSmokeBombActive(true);
        used = true;
    }
}
