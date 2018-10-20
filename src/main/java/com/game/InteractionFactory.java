package com.game;

import java.io.Serializable;

public class InteractionFactory implements Serializable{

    public Interaction getInteraction(EntityType entityType){

        switch (entityType) {
            case ENEMY :
                return new PlayerEnemyFight(new UserFightConsoleInput());
             default:
                 throw new IllegalArgumentException("Interaction Factory Not configured properly");

        }

    }

}
