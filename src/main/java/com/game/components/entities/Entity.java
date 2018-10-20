package com.game.components.entities;

import com.game.constants.EntityType;

import java.io.Serializable;

public interface Entity extends Serializable {

   String getName();
   boolean isAlive();
   EntityType getType();
   void takeDamage(int damagePoints);
   int getHealth();
   int getAttackPower();
   int getDefencePower();
   void addExperience(long points);
   long getExperience();
   String getPlayerClass();

}
