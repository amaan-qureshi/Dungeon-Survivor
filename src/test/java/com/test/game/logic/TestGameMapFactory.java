package com.test.game.logic;

import com.game.components.entities.impl.Player;
import com.game.components.maps.GameMap;
import com.game.components.maps.MapFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestGameMapFactory {

    @Test
    public void testMapPopulationWorks(){
        Player player = new Player("Player","Test",100,10,10);
        GameMap gameMap = MapFactory.getMap(10,player);
        Assert.assertNotNull(gameMap);
        Assert.assertNotNull(gameMap.findPlayerBlock());
        Assert.assertTrue(gameMap.tasksLeft());
    }
}
