package com.combat.actions;

import com.combat.model.Combatant;
import java.util.List;

public interface Action {
    void execute(Combatant source, List<Combatant> targets);

    // matt added --> each action describes what happened rather than hardcoded turn outcomes in HandlePlayer
    String getOutcome(Combatant source, List<Combatant> targets);
}
