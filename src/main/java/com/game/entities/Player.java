package com.game.entities;

import com.game.constants.EntityType;

public class Player implements Entity {

    private int health;
    private int attackPower;
    private int defencePower;
    private long experience;
    private String name;

    private boolean isAlive = true;

    public Player(String name,int health,int attackPower,int defencePower){
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
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

    public void addExperience(long points){
        this.experience +=points;
    }

    public long getExperience() {
        return experience;
    }
}
