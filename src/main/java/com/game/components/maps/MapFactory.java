package com.game.components.maps;

import com.game.constants.MessageConstants;
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

    public static GameMap getMap(int enemyCount, Player player) {

        GameMap gameGameMap = new GameMap(new UserMovementConsole(getMessage(MessageConstants.CHARACTER_MOVEMENT_OPTION), MovementActions.values()), new InteractionFactory());
        populateMap(gameGameMap, player, enemyCount);
        return gameGameMap;
    }

    private static void populateMap(GameMap gameGameMap, Player player, int enemyCount) {

        List<Position> usedPositionTracker = new ArrayList<>();
        gameGameMap.getMapBlocks().get(7).get(0).setEntity(player);
        usedPositionTracker.add(new Position(7, 0));
        for (int i = 0; i < enemyCount; i++) {
            Position enemyPosition = generateRandomUnusedPosition(usedPositionTracker);
            gameGameMap.getMapBlocks().get(enemyPosition.getX()).get(enemyPosition.getY()).setEntity(new Enemy(10,20,10));
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
