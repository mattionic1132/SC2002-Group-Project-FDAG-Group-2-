package com.combat.engine;

import com.combat.model.Combatant;
import java.util.List;

public interface TurnOrderStrategy {
    // returns combatants sorted in action order for the round
    List<Combatant> determineOrder(List<Combatant> combatants);
}