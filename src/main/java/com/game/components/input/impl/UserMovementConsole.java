package com.game.components.input.impl;


import com.game.constants.MovementActions;
import com.game.util.Position;

public class UserMovementConsole extends GenericMenuConsole {

    @SuppressWarnings("unchecked")
    public UserMovementConsole(String menuTitle, MovementActions... moves) {
        super(menuTitle, moves);
    }

    public Position getNextPosition(Position currentPosition) {
        MovementActions item = (MovementActions) getActionValue();
        switch (item) {
            case UP:
                return Position.of(currentPosition.getX() - 1, currentPosition.getY());
            case DOWN:
                return Position.of(currentPosition.getX() + 1, currentPosition.getY());
            case RIGHT:
                return Position.of(currentPosition.getX(), currentPosition.getY() + 1);
            case LEFT:
                return Position.of(currentPosition.getX(), currentPosition.getY() - 1);
            case GO_TO_MAIN:
                return Position.of(99, 99);
            default:
                throw new IllegalStateException("Unsupported menu item: " + item);
        }
    }

}
