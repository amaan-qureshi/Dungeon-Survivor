package com.game.constants;

public enum DifficultyLevel {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    private final String title;

    DifficultyLevel(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
