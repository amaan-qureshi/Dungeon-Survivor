package com.game;

public class LevelFactory {

    private final Menu<DifficultyLevel> difficultyMenu = new Menu<>("Choose Game Difficulty:", DifficultyLevel.values());

    public int getEnemyCount(){
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
