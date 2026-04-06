package com.combat.model;

import com.combat.actions.Action;
import com.combat.actions.BasicAttack;

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

    /** matt --> implemented fixed goblin stats and pass as parameters to super class constructor
     * also ensure the abstract method is implemented in goblin and wolf
     * Goblin always performs BasicAttack on its turn.
     * Calling BasicAttack class from package com.combat.actions
     * Calling Combatant class from package com.combat.model (as target)
     */
    @Override
    public Action performAction() {
        // goblin always uses basic attack - execution handled by BattleEngine
        return new BasicAttack();
    }
}