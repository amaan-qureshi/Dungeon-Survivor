package com.game.components.maps;

import com.game.constants.MovementActions;
import com.game.components.entities.impl.Enemy;
import com.game.components.entities.impl.Player;
import com.game.components.input.impl.UserMovementConsole;
import com.game.components.interactions.InteractionFactory;
import com.game.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.game.messages.MessageUtil.getMessage;

public class MapFactory {

    public static Map getMap(int enemyCount, Player player) {

        Map gameMap = new Map(new UserMovementConsole(getMessage("CHARACTER_MOVEMENT_OPTION"), MovementActions.values()), new InteractionFactory());
        populateMap(gameMap, player, enemyCount);
        return gameMap;
    }

    private static void populateMap(Map gameMap, Player player, int enemyCount) {

        List<Position> usedPositionTracker = new ArrayList<>();
        gameMap.getMapBlocks().get(7).get(0).setEntity(player);
        usedPositionTracker.add(new Position(7, 0));
        for (int i = 0; i < enemyCount; i++) {
            Position enemyPosition = generateRandomUnusedPosition(usedPositionTracker);
            gameMap.getMapBlocks().get(enemyPosition.getX()).get(enemyPosition.getY()).setEntity(new Enemy(10,20,10));
        }
    }

    private static Position generateRandomUnusedPosition(List<Position> usedPositionTracker) {
        int x = ThreadLocalRandom.current().nextInt(0, 8);
        int y = ThreadLocalRandom.current().nextInt(0, 8);
        if (usedPositionTracker.contains(new Position(x, y))) {
            return generateRandomUnusedPosition(usedPositionTracker);
        }
        Position position = new Position(x, y);
        usedPositionTracker.add(position);
        return position;
    }

}
