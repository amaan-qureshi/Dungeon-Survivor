package com.game.entities;

import com.game.constants.EntityType;

import java.io.Serializable;

public interface Entity extends Serializable {

    public String name();
    public boolean isStatic();
    public boolean isAlive();
    public EntityType getType();

    public void takeDamage(int damagePoints);

    public int getHealth();

    public int getAttackPower();

    public int getDefencePower();

    public void addExperience(long points);

    public long getExperience();

}
