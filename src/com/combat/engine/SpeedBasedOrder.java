package com.combat.engine;

import com.combat.model.Combatant;
import java.util.Comparator; //need to import this to compare combatants
import java.util.List;

public class SpeedBasedOrder implements TurnOrderStrategy {

    // sort combatants by speed, highest goes first
    @Override
    public List<Combatant> determineOrder(List<Combatant> combatants) {
        combatants.sort(new Comparator<Combatant>() {
            @Override
            public int compare(Combatant first_combatant, Combatant second_combatant) {
                // higher speed goes first, subtract first from second for descending order
                return second_combatant.getSpeed() - first_combatant.getSpeed();
            }
        });
        return combatants;
    }
}
