package com.combat.model;

import com.combat.actions.Action;
import com.combat.effects.StatusEffect;
import java.util.ArrayList;
import java.util.List;

public abstract class Combatant {

    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;

    // changed List<Object> to List<StatusEffect> after status effect merged
    private List<StatusEffect> statusEffects;

    public Combatant(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
    }

    // Combat Methods

    public void takeDamage(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    // Status Checks
    public boolean canAct() {
        // check the hardcoded boolean first
        if (this.stunned) {
            return false;
        }

        // check the interface list
        for (StatusEffect effect : statusEffects) {
            if (effect.preventsAction()) {
                return false;
            }
        }
        return true;
    }

    // decrement duration after turn ends
    // expiry check happens after tick so effect lasts the correct number of turns
    // fix for medium difficulty
    public void tickStatusEffects() {
        List<StatusEffect> expired = new ArrayList<>();
        for (StatusEffect effect : statusEffects) {
            effect.tick();
            if (effect.isExpired()) {
                effect.remove(this);
                expired.add(effect);
            }
        }
        statusEffects.removeAll(expired);
    }

    // apply the effect exactly once the moment it is added
    public void addStatusEffect(StatusEffect effect) {
        this.statusEffects.add(effect);
        effect.apply(this);
    }

    // changed Object to Action after status effect merged
    public abstract Action performAction();

    // getters

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getDefense()  { return defense; }
    public int getSpeed() { return speed; }

    public List<StatusEffect> getStatusEffects() { return statusEffects; }

    // setters - set the hp,attack and defence
    // note PLayer and Enemy access these using super call but no impleementation

    public void setHp(int hp) { this.hp = Math.max(0, Math.min(hp, maxHp)); }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }

    // matt added the booleans and bool setter for smoke bomb
    private boolean smokeBombActive = false;
    public boolean isSmokeBombActive() { return smokeBombActive; }
    public void setSmokeBombActive(boolean active) { this.smokeBombActive = active; }

    // for stun
    private boolean stunned = false;
    public boolean isStunned() { return stunned; }
    public void setStunned(boolean stunned) { this.stunned = stunned; }
}