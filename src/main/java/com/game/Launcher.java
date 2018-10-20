package com.game;

import com.game.constants.DifficultyLevel;
import com.game.constants.MainMenuActions;
import com.game.components.entities.impl.PlayerFactory;
import com.game.components.input.impl.GenericMenuConsole;
import com.game.components.input.impl.EnemySelectorConsole;
import com.game.components.maps.Map;
import com.game.components.maps.MapFactory;

import java.io.*;

import static com.game.messages.MessageUtil.getMessage;

public class Launcher {

    private static Map activeGameMap;

    @SuppressWarnings("unchecked")
    private static final GenericMenuConsole menuConsole = new GenericMenuConsole(getMessage("MAIN_MENU_OPTION"),MainMenuActions.values());

    public static void main(String... args) {
        SplashScreen.showSplash();
        do {
            goToMainMenu();
        }
        while (activeGameMap==null || !activeGameMap.isGameExited());
    }

    private static void goToMainMenu() {
        switch ((MainMenuActions)menuConsole.getActionValue()) {
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
            activeGameMap = MapFactory.getMap(new EnemySelectorConsole(getMessage("DIFFICULTY_OPTION"), DifficultyLevel.values()).getEnemyCount(), PlayerFactory.getPlayer());
        }
        activeGameMap.render();
        while (activeGameMap.isPlayerAlive() && activeGameMap.tasksLeft()) {
            activeGameMap.goToNextTurn();
            if (activeGameMap.isMapPaused()) {
                break;
            }
            activeGameMap.render();
            if(activeGameMap.isPlayerAlive() && !activeGameMap.tasksLeft()){
                System.out.println(getMessage("WIN_MESSAGE",activeGameMap.findPlayerBlock().getEntity().getExperience()));
            }
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
