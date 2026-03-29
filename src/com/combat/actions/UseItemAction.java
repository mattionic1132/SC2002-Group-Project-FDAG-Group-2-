package com.combat.actions;

// import item package

public class UseItemAction implements Action {

    private Item item;

    //gets the specific item the player wants to use

    public UseItemAction(Item item) {
        this.item = item;
    }

    @Override
    // from my understanding, the items only affect the player
    // trigger the item's use method from the Item interface
    public void execute(Combatant source, Combatant[] targets) {
        item.use(source, source);
    }
}
