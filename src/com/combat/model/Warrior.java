package com.combat.model;

// import com.combat.actions.Action;      ← uncomment when M3 merges
// import com.combat.actions.ShieldBash;  ← uncomment when M3 merges

public class Warrior extends Player {

    public Warrior(String name) {
        // hp=260, attack=40, defense=20, speed=30 per assignment spec
        super(name, 260, 40, 20, 30);
    }

    /**
     * Warrior's special skill.
     * Deals BasicAttack damage to target and stuns for 2 turns.
     * Actual execution handled by ShieldBash class in com.combat.actions.
     * Calling ShieldBash from com.combat.actions
     */
    public void shieldBash() {
        // TODO: call ShieldBash.execute() when M3 merges
        // new ShieldBash().execute(this, targets);
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