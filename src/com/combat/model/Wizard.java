package com.combat.model;

import com.combat.actions.Action;

public class Wizard extends Player {

    public Wizard(String name) {
        // hp=200, attack=50, defense=10, speed=20 per assignment spec
        super(name, 200, 50, 10, 20);
    }

     // Wizard's special skill.
     // Deals BasicAttack damage to ALL enemies currently in combat.
     // Each enemy defeated adds +10 to Wizard's attack permanently for the level.
     // Actual execution handled by ArcaneBlast class in com.combat.actions.
     // Calling ArcaneBlast from com.combat.actions



     // Player chooses action via GameCLI.
     // BattleEngine passes the chosen Action back to execute.
     // Calling Action interface from com.combat.actions


    @Override
    public Action performAction() {
        return null; // BattleEngine handles player action via CLI
    }
}
