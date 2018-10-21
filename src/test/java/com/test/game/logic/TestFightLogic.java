package com.test.game.logic;


import com.game.components.entities.impl.Enemy;
import com.game.components.entities.impl.Player;
import com.game.components.interactions.actions.AttackOperation;
import com.game.components.interactions.actions.DefendOperation;
import com.game.components.interactions.actions.PlayerActionInvoker;
import org.junit.Test;

public class TestFightLogic {


    @Test
    public void testAttackEnemy() throws Exception {
        Player player = new Player("Player","Test",100,10,10);
        int initialEnemyHealth = 10;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        PlayerActionInvoker.executeOperation(new AttackOperation(player, enemy));
        assert(enemy.getHealth()<initialEnemyHealth);
    }

    @Test
    public void testKillingEnemyEarnsExperience() throws Exception {

        Player player = new Player("Player","Test",100,10,10);
        int initialEnemyHealth = 10;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        do {
            PlayerActionInvoker.executeOperation(new AttackOperation(player, enemy));
        }
        while(enemy.isAlive());
        assert(player.getExperience()>0);

    }

    @Test
    public void testAttackEnemyIfEnemySurvives() throws Exception {
        int initialPlayerHealth = 10;
        Player player = new Player("Test","Test",initialPlayerHealth,10,10);
        int initialEnemyHealth = 1000;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        do {
            PlayerActionInvoker.executeOperation(new AttackOperation(player, enemy));
        }
        while(!enemy.isAlive());

        assert(player.getHealth()<initialPlayerHealth);

    }

    @Test
    public void testDefend() throws Exception {
        Player player = new Player("Player","Test",10,10,10);
        int initialEnemyHealth = 10;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        PlayerActionInvoker.executeOperation(new DefendOperation(player, enemy));
        assert(player.isAlive());
    }

}
