package com.combat.model;

import java.util.ArrayList;
import java.util.List;
import com.combat.effects.StatusEffect;
import com.combat.actions.Action;

public abstract class Combatant {

    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private boolean stunned = false;
    private boolean smokeBombActive = false;

    // change List<Object> to List<StatusEffect> when M4 merges
    private List<Object> statusEffects;

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

    public void applyStatusEffects() {
        // TODO: uncomment when M4 merges
        // List<StatusEffect> expired = new ArrayList<>();
        // for (StatusEffect effect : statusEffects) {
        //     effect.apply(this);
        //     effect.tick();
        //     if (effect.isExpired()) expired.add(effect);
        // }
        // statusEffects.removeAll(expired);
    }

    // change Object to StatusEffect when M4 merges
    public void addStatusEffect(Object effect) {
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
    public boolean isStunned()            { return stunned; }
    public boolean isSmokeBombActive()    { return smokeBombActive; }
    public List<Object> getStatusEffects(){ return statusEffects; }

    // ─── Setters ──────────────────────────────────────────────────────

    public void setHp(int hp)                         { this.hp = Math.max(0, Math.min(hp, maxHp)); }
    public void setAttack(int attack)                 { this.attack = attack; }
    public void setDefense(int defense)               { this.defense = defense; }
    public void setStunned(boolean stunned)           { this.stunned = stunned; }
    public void setSmokeBombActive(boolean active)    { this.smokeBombActive = active; }
}