package com.combat.model;

// import com.combat.actions.Action;       ← uncomment when M3 merges
// import com.combat.actions.ArcaneBlast;  ← uncomment when M3 merges
// import com.combat.model.Enemy;          ← already in same package, no import needed

public class Wizard extends Player {

    public Wizard(String name) {
        // hp=200, attack=50, defense=10, speed=20 per assignment spec
        super(name, 200, 50, 10, 20);
    }

    /**
     * Wizard's special skill.
     * Deals BasicAttack damage to ALL enemies currently in combat.
     * Each enemy defeated adds +10 to Wizard's attack permanently for the level.
     * Actual execution handled by ArcaneBlast class in com.combat.actions.
     * Calling ArcaneBlast from com.combat.actions
     */
    public void arcaneBlast() {
        // TODO: call ArcaneBlast.execute() when M3 merges
        // new ArcaneBlast().execute(this, targets);
    }

    /**
     * Player chooses action via GameCLI.
     * BattleEngine passes the chosen Action back to execute.
     * Calling Action interface from com.combat.actions
     */
    @Override
    public Object performAction() {
        // TODO: change return type from Object to Action when M3 merges
        // GameCLI handles user input and returns chosen Action
        // BattleEngine calls action.execute(this, targets)
        return null;
    }
}
