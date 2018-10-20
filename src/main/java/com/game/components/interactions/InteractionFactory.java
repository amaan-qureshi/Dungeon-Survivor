package com.game.components.interactions;

import com.game.constants.EntityType;

import java.io.Serializable;

public class InteractionFactory implements Serializable{

    public Interaction getInteraction(EntityType entityType){

        switch (entityType) {
            case ENEMY :
                return new PlayerEnemyFight();
             default:
                 throw new IllegalArgumentException("Interaction Factory Not configured properly");

        }

    }

}
