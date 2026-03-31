package com.combat.model;

// Calling Enemy class from package com.combat.model
// Calling Action interface from package com.combat.actions
// Calling BasicAttack class from package com.combat.actions
public class Goblin extends Enemy {

    private static final int GOBLIN_HP      = 55;
    private static final int GOBLIN_ATTACK  = 35;
    private static final int GOBLIN_DEFENSE = 15;
    private static final int GOBLIN_SPEED   = 25;

    public Goblin(String name) {
        // Calling Enemy constructor from package com.combat.model
        super(name, GOBLIN_HP, GOBLIN_ATTACK, GOBLIN_DEFENSE, GOBLIN_SPEED);
    }

    /**
     * Goblin always performs BasicAttack on its turn.
     * Calling BasicAttack class from package com.combat.actions
     * Calling Combatant class from package com.combat.model (as target)
     */
    @Override
    public Object performAction() {
        // TODO: implement BasicAttack execution
        // new BasicAttack().execute(this, targets);
        return null;
    }
}