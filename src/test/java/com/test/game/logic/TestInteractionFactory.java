package com.test.game.logic;

import com.game.components.entities.impl.Enemy;
import com.game.components.interactions.Interaction;
import com.game.components.interactions.InteractionFactory;
import com.game.components.interactions.PlayerEnemyFight;
import com.game.constants.EntityType;
import org.junit.Before;
import org.junit.Test;

public class TestInteractionFactory {

    private InteractionFactory interactionFactory;

    @Before
    public void setUp() {
        interactionFactory = new InteractionFactory();
    }

    @Test
    public void testGetInteraction() throws Exception {
        Enemy enemy = new Enemy(10,10,10);
        Interaction interaction = interactionFactory.getInteraction(enemy.getType());
        assert(interaction instanceof PlayerEnemyFight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInteractionThrowsException() throws Exception {
        Enemy enemy = new Enemy(10,10,10);
        Interaction interaction = interactionFactory.getInteraction(EntityType.UNKNOWN);
    }

}
