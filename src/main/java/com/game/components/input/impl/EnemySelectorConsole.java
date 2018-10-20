package com.game.components.input.impl;

import com.game.constants.DifficultyLevel;

public class EnemySelectorConsole extends GenericMenuConsole {

    @SuppressWarnings("unchecked")
    public EnemySelectorConsole(String menuTitle, DifficultyLevel... levels) {
        super(menuTitle, levels);
    }

    public int getEnemyCount() {
        DifficultyLevel difficultyLevel = (DifficultyLevel) getActionValue();

        switch (difficultyLevel) {
            case EASY:
                return 20;
            case MEDIUM:
                return 30;
            case HARD:
                return 40;
            default:
                throw new IllegalStateException("Unsupported difficulty Level: " + difficultyLevel);
        }
    }

}