package com.combat.model;

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

    // change List<Object> to List<StatusEffect> when M4 merges
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

    // ─── Combat Methods ───────────────────────────────────────────────

    public void takeDamage(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    // ─── Status Checks ────────────────────────────────────────────────

    // Replaces isStunned(). Checks if any active effect prevents moving.
    // TODO M4: Ensure StatusEffect interface has a boolean preventsAction() method!
    public boolean canAct() {
        for (StatusEffect effect : statusEffects) {
            if (effect.preventsAction()) {
                return false;
            }
        }
        return true;
    }

    // logic for applying status effects based on tick and duration
    public void applyStatusEffects() {
        List<StatusEffect> expired = new ArrayList<>();
        for (StatusEffect effect : statusEffects) {
            effect.apply(this);
            effect.tick();
            // effect is removed via interface method when expired = True
            if (effect.isExpired()) {
                effect.remove(this);
                expired.add(effect);
            }
        }
        statusEffects.removeAll(expired);
    }

    // change Object to StatusEffect when M4 merges
    public void addStatusEffect(StatusEffect effect) {
        this.statusEffects.add(effect);
    }

    // change Object to Action when M3 merges
    public abstract Object performAction();

    // ─── Getters ──────────────────────────────────────────────────────

    public String getName()               { return name; }
    public int getHp()                    { return hp; }
    public int getMaxHp()                 { return maxHp; }
    public int getAttack()                { return attack; }
    public int getDefense()               { return defense; }
    public int getSpeed()                 { return speed; }

    public List<StatusEffect> getStatusEffects() { return statusEffects; }

    // ─── Setters ──────────────────────────────────────────────────────

    public void setHp(int hp)                         { this.hp = Math.max(0, Math.min(hp, maxHp)); }
    public void setAttack(int attack)                 { this.attack = attack; }
    public void setDefense(int defense)               { this.defense = defense; }
}