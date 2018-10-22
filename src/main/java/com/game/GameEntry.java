package com.game;

import com.game.components.maps.GameMap;
import com.game.constants.DifficultyLevel;
import com.game.constants.MainMenuActions;
import com.game.components.entities.impl.PlayerFactory;
import com.game.components.input.impl.GenericMenuConsole;
import com.game.components.input.impl.EnemySelectorConsole;
import com.game.components.maps.MapFactory;
import com.game.constants.MessageConstants;

import java.io.*;

import static com.game.messages.MessageUtil.getMessage;

public class GameEntry {

    private static GameMap activeGameGameMap;
    private static final String SAVE_FILE_NAME = "game.sav";

    @SuppressWarnings("unchecked")
    private static final GenericMenuConsole menuConsole = new GenericMenuConsole(getMessage(MessageConstants.MAIN_MENU_OPTION), MainMenuActions.values());

    public static void beginGame() {
        SplashScreen.showSplash();
        do {
            goToMainMenu();
        }
        while (activeGameGameMap == null || !activeGameGameMap.isGameExited());
    }

    private static void goToMainMenu() {
        switch ((MainMenuActions) menuConsole.getActionValue()) {
            case START_NEW:
                startGame();
                break;
            case SAVE_GAME_RESUME:
                saveAndContinueGame();
                break;
            case SAVE_GAME_EXIT:
                saveAndExitGame();
                break;
            case LOAD_EXISTING:
                loadGame();
                break;
        }
    }

    private static void startGame() {
        if (activeGameGameMap == null || !activeGameGameMap.tasksLeft()) {
            activeGameGameMap = MapFactory.getMap(new EnemySelectorConsole(getMessage(MessageConstants.DIFFICULTY_OPTION), DifficultyLevel.values()).getEnemyCount(), PlayerFactory.getPlayer());
        }
        activeGameGameMap.render();
        while (activeGameGameMap.isPlayerAlive() && activeGameGameMap.tasksLeft()) {
            activeGameGameMap.goToNextTurn();
            if (activeGameGameMap.isMapPaused()) {
                break;
            }
            activeGameGameMap.render();
            if (activeGameGameMap.isPlayerAlive() && !activeGameGameMap.tasksLeft()) {
                System.out.println(getMessage(MessageConstants.WIN_MESSAGE, activeGameGameMap.findPlayerBlock().getEntity().getExperience()));
            }
        }
    }

    private static void saveGame() {
        activeGameGameMap.setGameExited(false);
        activeGameGameMap.setMapPaused(false);
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(activeGameGameMap);
        } catch (IOException ex) {
            System.out.println(getMessage(MessageConstants.SAVE_ERROR));
        }
    }

    private static GameMap load() {
        GameMap loadedGameMap = null;
        if (checkSaveGameFiles()) {
            try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME); ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object result = ois.readObject();
                loadedGameMap = (GameMap) result;
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(getMessage(MessageConstants.LOAD_ERROR));
            }
        } else {
            System.out.println(getMessage(MessageConstants.NO_SAVES));
        }
        return loadedGameMap;
    }

    private static boolean checkSaveGameFiles() {
        File f1 = new File(SAVE_FILE_NAME);
        return f1.exists() && !f1.isDirectory();
    }

    private static void saveAndExitGame() {
        if (activeGameGameMap != null) {
            if (!activeGameGameMap.tasksLeft() || !activeGameGameMap.isPlayerAlive()) {
                System.out.println(getMessage(MessageConstants.GAME_ALREADY_WON));
                return;
            }
            saveGame();
            activeGameGameMap.setGameExited(true);
        } else {
            System.out.println(getMessage(MessageConstants.START_GAME_TO_SAVE));
        }
    }

    private static void saveAndContinueGame() {
        if (activeGameGameMap != null) {
            if (!activeGameGameMap.tasksLeft() || !activeGameGameMap.isPlayerAlive()) {
                System.out.println(getMessage(MessageConstants.GAME_ALREADY_WON));
                return;
            }
            saveGame();
            resumeGame();
        } else {
            System.out.println(getMessage(MessageConstants.START_GAME_TO_SAVE));
        }
    }

    private static void loadGame() {
        GameMap gameMap = load();
        if (gameMap != null) {
            activeGameGameMap = gameMap;
            resumeGame();
        }
    }

    private static void resumeGame() {
        activeGameGameMap.setMapPaused(false);
        startGame();
    }


}
