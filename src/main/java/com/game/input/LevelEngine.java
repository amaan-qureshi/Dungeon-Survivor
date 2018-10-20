package com.game.input;

import com.game.constants.DifficultyLevel;

public class LevelEngine {

    private static final ActionMenu<DifficultyLevel> difficultyMenu = new ActionMenu<>("Choose Game Difficulty:", DifficultyLevel.values());

    public static int getEnemyCount(){
        difficultyMenu.draw();
        DifficultyLevel difficultyLevel = difficultyMenu.chooseItem();

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
