package com.combat.model;
// note for goblin and enemy the basic attack execution is in battle engine
import com.combat.actions.Action;
import com.combat.actions.BasicAttack;

public class Goblin extends Enemy {

    private static final int GOBLIN_HP      = 55;
    private static final int GOBLIN_ATTACK  = 35;
    private static final int GOBLIN_DEFENSE = 15;
    private static final int GOBLIN_SPEED   = 25;

    public Goblin(String name) {
        // Calling Enemy constructor from package com.combat.model
        super(name, GOBLIN_HP, GOBLIN_ATTACK, GOBLIN_DEFENSE, GOBLIN_SPEED);
    }


      // goblin always performs BasicAttack on its turn.
     // Calling BasicAttack class from package com.combat.actions
     // Calling Combatant class from package com.combat.model (as target)


    @Override
    public Action performAction() {
        // goblin always uses basic attack
        return new BasicAttack();
    }
}