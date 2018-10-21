package com.test.game.logic;


import com.game.components.entities.impl.Enemy;
import com.game.components.entities.impl.Player;
import com.game.components.interactions.PlayerEnemyFight;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class TestFightLogic {

    private PlayerEnemyFight fightLogic;

    @Before
    public void setUp() {
        fightLogic = new PlayerEnemyFight();
    }

    @Test
    public void testAttackEnemy() throws Exception {
        Player player = new Player("Player","Test",100,10,10);
        int initialEnemyHealth = 10;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        Whitebox.setInternalState(fightLogic, "user", player);
        Whitebox.setInternalState(fightLogic, "enemy", enemy);
        Whitebox.invokeMethod(fightLogic,"attackEnemy");
        assert(enemy.getHealth()<initialEnemyHealth);
    }

    @Test
    public void testKillingEnemyEarnsExperience() throws Exception {

        Player player = new Player("Player","Test",100,10,10);
        int initialEnemyHealth = 10;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        Whitebox.setInternalState(fightLogic, "user", player);
        Whitebox.setInternalState(fightLogic, "enemy", enemy);

        do {
            Whitebox.invokeMethod(fightLogic, "attackEnemy");
        }
        while(enemy.isAlive());
        assert(player.getExperience()>0);

    }

    @Test
    public void testAttackEnemyIfEnenmySurvives() throws Exception {
        int initialPlayerHealth = 10;
        Player player = new Player("Test","Test",initialPlayerHealth,10,10);
        int initialEnemyHealth = 100;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        Whitebox.setInternalState(fightLogic, "user", player);
        Whitebox.setInternalState(fightLogic, "enemy", enemy);
        do {
            Whitebox.invokeMethod(fightLogic, "attackEnemy");
        }
        while(!enemy.isAlive());

        assert(player.getHealth()<initialPlayerHealth);

    }

    @Test
    public void testDefend() throws Exception {
        Player player = new Player("Player","Test",10,10,10);
        int initialEnemyHealth = 10;
        Enemy enemy = new Enemy(initialEnemyHealth,10,10);
        Whitebox.setInternalState(fightLogic, "user", player);
        Whitebox.setInternalState(fightLogic, "enemy", enemy);
        Whitebox.invokeMethod(fightLogic,"defend");
        assert(player.isAlive());
    }

}
