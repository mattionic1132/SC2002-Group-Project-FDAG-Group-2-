package com.combat.actions;

import com.combat.model.Combatant;
import java.util.List;

public interface Action {
    void execute(Combatant source, List<Combatant> targets);
}
