package com.game.components.entities.impl;

import com.game.constants.EntityType;
import com.game.components.entities.Entity;

public class Player implements Entity {

    private final int attackPower;
    private final int defencePower;
    private long experience;
    private int health;
    private final String name;
    private final String playerClass;

    private boolean isAlive = true;

    public Player(String name,String playerClass, int health, int attackPower, int defencePower) {
        this.name = name;
        this.playerClass = playerClass;
        this.health = health;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    public void takeDamage(int damagePoints) {
        this.health = this.health - damagePoints;
        if (this.health <= 0) {
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
        this.experience += points;
    }

    @Override
    public long getExperience() {
        return experience;
    }

    @Override
    public String getPlayerClass() {
        return playerClass;
    }
}
