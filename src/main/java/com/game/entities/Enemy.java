package com.game.entities;

import com.game.constants.EntityType;

public class Enemy implements Entity {

    private int health = 10;
    private int attackPower = 20;
    private int defencePower = 10;

    private boolean isAlive = true;

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isStatic() {
        return false;
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
    public void addExperience(long points) {

    }

    @Override
    public long getExperience() {
        return 0;
    }
}
