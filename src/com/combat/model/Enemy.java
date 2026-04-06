package com.combat.model;


public abstract class Enemy extends Combatant {

    public Enemy(String name, int hp, int attack, int defense, int speed) {
        // Calling Combatant constructor from package com.combat.model
        super(name, hp, attack, defense, speed);
    }

    /**
     * Enemies always perform BasicAttack.
     * Calling BasicAttack class from package com.combat.actions
     * Calling Action interface from package com.combat.actions
     */
// accidentally merged old --> change back to Action
    @Override
    public abstract Action performAction();
}
