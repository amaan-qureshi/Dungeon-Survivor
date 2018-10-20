package com.game.core;

import com.game.*;
import com.game.maps.Map;
import com.game.maps.MapFactory;

import java.io.*;

import static com.game.messages.MessageUtil.getMessage;

public class Launcher {

    private static Map activeGameMap;
    private static final MainMenuConsoleInput mainMenuConsoleInput = new MainMenuConsoleInput();

    public static void main(String... args) {
        SpashScreen.showSpash();
        do {
            goToMainMenu();
        }
        while (activeGameMap==null || (activeGameMap !=null && !activeGameMap.isGameExited()));
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
            activeGameMap = MapFactory.getMap(LevelEngine.getEnemyCount(), PlayerFactory.getPlayer());
        }
        activeGameMap.render();
        while (activeGameMap.isPlayerAlive()/* && map.tasksLeft()*/) {
            activeGameMap.goToNextIteration();
            if (activeGameMap.isMapPaused()) {
                break;
            }
            activeGameMap.render();
        }
    }

    private static void saveGame() {
        if (activeGameMap != null) {
            activeGameMap.setGameExited(false);
            activeGameMap.setMapPaused(false);
            try (FileOutputStream fos = new FileOutputStream("game.sav"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(activeGameMap);
            } catch (IOException ex) {
                System.out.println(getMessage("SAVE_ERROR") + ex);
            }

        } else {
            System.out.print(getMessage("START_GAME_TO_SAVE"));
        }
    }

    private static Map load() {
        Map loadedMap = null;
        if (checkSaveGameFiles()) {
            try (FileInputStream fis = new FileInputStream("game.sav"); ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object result = ois.readObject();
                loadedMap = (Map) result;
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(getMessage("LOAD_ERROR") + ex);
            }
        } else {
            System.out.println(getMessage("NO_SAVES"));
        }
        return loadedMap;
    }

    private static boolean checkSaveGameFiles() {
        File f1 = new File("game.sav");
        return f1.exists() && !f1.isDirectory();
    }

    private static void saveAndExitGame() {
        saveGame();
        if(activeGameMap!=null){
            activeGameMap.setGameExited(true);
        }


    }

    private static void saveAndContinueGame() {
        saveGame();
        if(activeGameMap!=null){
            resumeGame();
        }

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


}
