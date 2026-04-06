package com.combat.model;

import com.combat.actions.Action;
import com.combat.actions.BasicAttack;

// Calling Enemy class from package com.combat.model
// Calling Action interface from package com.combat.actions
// Calling BasicAttack class from package com.combat.actions
public class Wolf extends Enemy {

    private static final int WOLF_HP      = 40;
    private static final int WOLF_ATTACK  = 45;
    private static final int WOLF_DEFENSE = 5;
    private static final int WOLF_SPEED   = 35;

    public Wolf(String name) {
        // Calling Enemy constructor from package com.combat.model
        super(name, WOLF_HP, WOLF_ATTACK, WOLF_DEFENSE, WOLF_SPEED);
    }

    /**matt --> implemented fixed wolfstats and pass as parameters to super class constructor
     * also ensure the abstract method is implemented in goblin and wolf
     * Wolf always performs BasicAttack on its turn.
     * Calling BasicAttack class from package com.combat.actions
     * Calling Combatant class from package com.combat.model (as target)
     */
    @Override
    public Action performAction() {
        // wolf always uses basic attack - execution handled by BattleEngine
        return new BasicAttack();
    }
}