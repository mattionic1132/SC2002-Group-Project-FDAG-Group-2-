package com.combat.model;
import java.util.ArrayList;
import java.util.List;

import com.combat.actions.Action;
import com.combat.items.Item;
import com.combat.actions.SpecialSkill;


public abstract class Player extends Combatant {

    // require item interface 
    private List<Item> items;
    private int skillCooldown;

    public Player(String name, int maxHp, int attack, int defense, int speed) {
        super(name, maxHp, attack, defense, speed);
        this.items = new ArrayList<>();
        this.skillCooldown = 0;
    }

    public abstract Action performAction();

    // require item interface
    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return this.items;
    }

    // sets cooldown to 3 turns
    public void useSpecialSkill() {
        this.skillCooldown = 3;
    }

    public void decrementCooldown() {
        if (this.skillCooldown > 0) {
            this.skillCooldown--;
        }
    }

    // matt added this to use SpecialSkill from actions package
    private SpecialSkill specialSkill;
    public void setSpecialSkill(SpecialSkill skill) { this.specialSkill = skill; }
    public SpecialSkill getSpecialSkill()           { return this.specialSkill; }


    public boolean isSkillReady() {
        return this.skillCooldown == 0;
    }
}