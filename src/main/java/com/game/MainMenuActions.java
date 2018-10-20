package com.game;

public enum MainMenuActions {
    START_NEW("Start New Game"),
    SAVE_GAME_RESUME("Save Game"),
    SAVE_GAME_EXIT("Save Game and Quit"),
    LOAD_EXISTING("Load Game");

    private final String title;

    MainMenuActions(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}