package com.test.game.logic;

import com.game.components.entities.impl.Player;
import com.game.components.maps.GameMap;
import com.game.components.maps.MapBlock;
import com.game.components.maps.MapFactory;
import com.game.util.Position;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class TestMaps {

    private GameMap gameMap;

    @Before
    public void setUp() {
        Player player = new Player("Player", "Test", 100, 10, 10);
        gameMap = MapFactory.getMap(0, player);
    }

    @Test
    public void testPlayerMovementOnMaps() {
        ByteArrayInputStream in;
        MapBlock playerBlock = gameMap.findPlayerBlock();
        Position initialPosition = playerBlock.getPosition();
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        System.setIn(System.in);
        gameMap.goToNextTurn();

        playerBlock = gameMap.findPlayerBlock();
        Position nextPosition = playerBlock.getPosition();
        assert (initialPosition.getX() - nextPosition.getX() == 1);

        initialPosition = nextPosition;

        in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        System.setIn(System.in);

        gameMap.goToNextTurn();

        playerBlock = gameMap.findPlayerBlock();
        nextPosition = playerBlock.getPosition();
        assert (nextPosition.getY() - initialPosition.getY() == 1);

        initialPosition = nextPosition;

        in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        System.setIn(System.in);

        gameMap.goToNextTurn();

        playerBlock = gameMap.findPlayerBlock();
        nextPosition = playerBlock.getPosition();
        assert (initialPosition.getY() - nextPosition.getY() == 1);

        initialPosition = nextPosition;

        in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        System.setIn(System.in);

        gameMap.goToNextTurn();

        playerBlock = gameMap.findPlayerBlock();
        nextPosition = playerBlock.getPosition();
        assert (nextPosition.getX() - initialPosition.getX() == 1);


    }

}
