package com.combat.engine;

import java.util.List;

// Calling Combatant class from package com.combat.model
// Calling TurnOrderStrategy interface from package com.combat.engine
public class SpeedBasedOrder implements TurnOrderStrategy {

    /**
     * Sorts combatants by speed descending — highest speed goes first.
     * Calling Combatant.getSpeed() from package com.combat.model
     *
     * @param combatants all active combatants in the battle
     * @return list sorted by speed descending
     */
    @Override
    public List<?> determineOrder(List<?> combatants) {
        // TODO: sort combatants by getSpeed() descending
        // combatants.sort((a, b) -> b.getSpeed() - a.getSpeed());
        return combatants;
    }
}
