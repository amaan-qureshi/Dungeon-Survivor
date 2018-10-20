package com.game.input;


import com.game.util.Position;

public class UserMovementConsoleInput implements UserMovementInput {

    private final ActionMenu<Movement> menu = new ActionMenu<>("Choose character movement:", Movement.values());

    @Override
    public Position getNextPosition(Position currentPosition) {
        menu.draw();
        Movement item = menu.chooseItem();
        switch (item) {
            case UP:
                return Position.of(currentPosition.getX()-1, currentPosition.getY());
            case DOWN:
                return Position.of(currentPosition.getX()+1, currentPosition.getY());
            case RIGHT:
                return Position.of(currentPosition.getX(), currentPosition.getY()+1);
            case LEFT:
                return Position.of(currentPosition.getX(), currentPosition.getY()-1);
            case GO_TO_MAIN:
                return Position.of(99, 99);
            default:
                throw new IllegalStateException("Unsupported menu item: " + item);
        }
    }

    private enum Movement {
        UP("Move up"),
        DOWN("Move down"),
        RIGHT("Move right"),
        LEFT("Move left"),
        GO_TO_MAIN("Back To Main Menu");

        private final String title;

        Movement(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
