package com.game.constants;

public enum MovementActions {

    UP("Move up"),
    DOWN("Move down"),
    RIGHT("Move right"),
    LEFT("Move left"),
    GO_TO_MAIN("Back To Main Menu");

    private final String title;

    MovementActions(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}


