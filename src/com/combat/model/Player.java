package com.combat.model;
import java.util.ArrayList;
import java.util.List;

import com.combat.actions.Action;
import com.combat.items.Item;
import com.combat.actions.SpecialSkill;

// removed skillCooldown attribute and all related methods to cooldown
// already handled by SpecialSkill class -> SRP
public abstract class Player extends Combatant {

    // require item interface 
    private List<Item> items;

    public Player(String name, int maxHp, int attack, int defense, int speed) {
        super(name, maxHp, attack, defense, speed);
        this.items = new ArrayList<>();
    }

    public abstract Action performAction();

    // require item interface
    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return this.items;
    }



    // matt added this to use SpecialSkill from actions package
    private SpecialSkill specialSkill;
    public void setSpecialSkill(SpecialSkill skill) { this.specialSkill = skill; }
    public SpecialSkill getSpecialSkill()           { return this.specialSkill; }



}