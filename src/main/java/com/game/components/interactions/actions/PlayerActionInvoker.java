package com.game.components.interactions.actions;

public class PlayerActionInvoker {
    public static void executeOperation(PlayerOperation operation) {
        operation.execute();
    }
}
