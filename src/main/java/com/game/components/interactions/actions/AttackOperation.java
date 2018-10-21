package com.game.components.interactions.actions;

import com.game.components.entities.Entity;

public class AttackOperation implements PlayerOperation{

    private final PlayerActions playerActions;

    public AttackOperation(Entity user, Entity enemy){
        playerActions = new PlayerActions(user,enemy);
    }

    @Override
    public void execute() {
        playerActions.attack();
    }
}
