package com.combat.engine;

import java.util.List;

// Calling Combatant class from package com.combat.model
public interface TurnOrderStrategy {

    /**
     * Determines the order in which combatants act this round.
     * Calling Combatant class from package com.combat.model
     *
     * @param combatants all active combatants in the battle
     * @return sorted list of combatants in action order
     */
    List<?> determineOrder(List<?> combatants); // List<Combatant>
}
