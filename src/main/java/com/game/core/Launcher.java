package com.game.core;

import com.game.*;

import java.io.*;

public class Launcher {

    private static Map activeGameMap;
    private static final MainMenuConsoleInput mainMenuConsoleInput = new MainMenuConsoleInput();
    private static final PlayerFactory playerFactory = new PlayerFactory();
    private static final LevelFactory levelFactory = new LevelFactory();

    public static void main(String... args) {
        SpashScreen.showSpash();
        do {
            goToMainMenu();
        }
        while (!activeGameMap.isGameExited());
    }

    private static void goToMainMenu() {
        switch (mainMenuConsoleInput.getAction()) {
            case START_NEW:
                startGame(null);
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

    private static void startGame(Map map) {
        if (map == null) {
            activeGameMap = MapFactory.getMap(levelFactory.getEnemyCount(), playerFactory.getPlayer());
        }
        activeGameMap.draw();
        while (activeGameMap.isPlayerAlive()/* && map.tasksLeft()*/) {
            activeGameMap.goToNextIteration();
            if (activeGameMap.isMapPaused()) {
                break;
            }
            activeGameMap.draw();
        }
    }

    private static void saveAndExitGame() {
        saveGame();
        activeGameMap.setGameExited(true);

    }

    private static void saveAndContinueGame() {
        saveGame();
        resumeGame();
    }

    private static void loadGame() {
        Map map = load();
        if (map != null) {
            activeGameMap = map;
            resumeGame();
        }
    }

    private static void resumeGame() {
        activeGameMap.setMapPaused(false);
        startGame(activeGameMap);
    }

    private static void saveGame() {
        if (activeGameMap != null) {
            activeGameMap.setGameExited(false);
            activeGameMap.setMapPaused(false);
            try (FileOutputStream fos = new FileOutputStream("game.sav"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(activeGameMap);
            } catch (IOException ex) {
                System.out.println("Exception occurred while saving game : " + ex);
            }

        } else {
            System.out.print("Please Start a new Game");
        }
    }

    private static Map load() {
        Map loadedMap = null;
        if (checkSaveGameFiles()) {
            try (FileInputStream fis = new FileInputStream("game.sav"); ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object result = ois.readObject();
                loadedMap = (Map) result;
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Exception occurred while loading game : " + ex);
            }
        } else {
            System.out.print("No saved game file found");
        }
        return loadedMap;
    }

    private static boolean checkSaveGameFiles() {
        File f1 = new File("game.sav");
        return f1.exists() && !f1.isDirectory();
    }

}
