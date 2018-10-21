package com.test.game.logic;

import com.game.components.entities.impl.Player;
import com.game.components.maps.Map;
import com.game.components.maps.MapFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMapFactory {

    @Test
    public void testMapPopulationWorks(){
        Player player = new Player("Player","Test",100,10,10);
        Map map = MapFactory.getMap(10,player);
        Assert.assertNotNull(map);
        Assert.assertNotNull(map.findPlayerBlock());
        Assert.assertTrue(map.tasksLeft());
    }
}
