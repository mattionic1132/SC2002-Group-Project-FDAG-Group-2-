package com.combat.items;

import com.combat.model.Combatant;
import com.combat.model.Player;
import java.util.List;

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
    public void use(Combatant source, List<Combatant> targets) {
        if (used) {
            System.out.println(name + " has already been used!");
            return;
        }
        //added check. if no check, then if source were to be casted to enemy, it will crash
        // violating LSP
        if (!(source instanceof Player)) {
            System.out.println("Only players can use PowerStone!");
            return;
        }
        //implement powerstone effect: can use skill wo cooldown
        ((Player) source).getSpecialSkill().execute(source, targets);
        System.out.println(source.getName() + " used " + name + "!");
        used = true;
    }
}
