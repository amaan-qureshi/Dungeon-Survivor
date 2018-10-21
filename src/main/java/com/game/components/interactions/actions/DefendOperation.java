package com.game.components.interactions.actions;

import com.game.components.entities.Entity;

public class DefendOperation implements PlayerOperation{

    private final PlayerActions playerActions;

    public DefendOperation(Entity user, Entity enemy){
        playerActions = new PlayerActions(user,enemy);
    }

    @Override
    public void execute() {
        playerActions.defend();
    }
}
