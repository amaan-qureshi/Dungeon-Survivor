package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MapFactory {

    public static Map getMap(int enemyCount, Player player) {

        Map gameMap = new Map(new UserMovementConsoleInput(), new InteractionFactory());
        populateMap(gameMap, player, enemyCount);
        return gameMap;
    }

    private static void populateMap(Map gameMap, Player player, int enemyCount) {

        gameMap.getMapBlocks().get(7).get(0).setEntity(player);
        usedPositionTracker.add(new Position(7, 0));
        for (int i = 0; i < enemyCount; i++) {
            Position enemyPosition = generateRandomUnusedPosition();
            gameMap.getMapBlocks().get(enemyPosition.getX()).get(enemyPosition.getY()).setEntity(new Enemy());
        }
    }

    private static List<Position> usedPositionTracker = new ArrayList<>();

    public static Position generateRandomUnusedPosition() {
        int x = ThreadLocalRandom.current().nextInt(0, 8);
        int y = ThreadLocalRandom.current().nextInt(0, 8);
        if (usedPositionTracker.contains(new Position(x, y))) {
            return generateRandomUnusedPosition();
        }
        Position position = new Position(x, y);
        usedPositionTracker.add(position);
        return position;
    }

}
