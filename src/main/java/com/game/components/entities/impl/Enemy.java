package com.game.components.entities.impl;

import com.game.constants.EntityType;
import com.game.components.entities.Entity;

public class Enemy implements Entity {

    private int health;
    private final int attackPower;
    private final int defencePower;
    private boolean isAlive = true;

    public Enemy(int health, int attackPower, int defencePower) {
        this.health = health;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
    }

    @Override
    public String getName() {
        return "Bandit";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENEMY;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    public void takeDamage(int damagePoints){
        this.health = this.health - damagePoints;
        if(this.health<=0){
            isAlive = false;
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public int getDefencePower() {
        return defencePower;
    }

    @Override
    public void addExperience(long points) {}

    @Override
    public long getExperience() {return 0;}

    @Override
    public String getPlayerClass() { return "Generic Enemy"; }

}
