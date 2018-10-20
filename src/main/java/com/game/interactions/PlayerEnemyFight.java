package com.game.interactions;

import com.game.util.RandomUtil;
import com.game.entities.Entity;
import com.game.input.UserFightConsoleInput;

public class PlayerEnemyFight implements Interaction {

    private Entity user;
    private Entity enemy;
    private final UserFightConsoleInput userFightConsoleInput;

    public PlayerEnemyFight(UserFightConsoleInput userFightConsoleInput) {
        this.userFightConsoleInput = userFightConsoleInput;
    }

    @Override
    public void interact(Entity user, Entity enemy) {
        this.user = user;
        this.enemy = enemy;

        while(user.isAlive() && enemy.isAlive()) {
            switch (userFightConsoleInput.getAction()) {
                case ATTACK:
                    attackEnemy();
                    break;
                case DEFEND:
                    defend();
                    break;
            }
        }
    }

    public void attackEnemy() {

        int playerAttackPower = (int) (user.getAttackPower() * (RandomUtil.generateRandomInRange(1,10)/10));

        System.out.println("Player Hits Enemy with " + playerAttackPower);
        enemy.takeDamage(playerAttackPower);
        if (enemy.isAlive()) {
            System.out.println("Enemy health " + enemy.getHealth());
        } else {
            user.addExperience(10);
            System.out.println("Enemy Killed");
            return;
        }

        int enemyAttackPower = (int) (enemy.getAttackPower() * (RandomUtil.generateRandomInRange(1,10)/10));
        System.out.println("Enemy Hits Player with " + enemyAttackPower);
        user.takeDamage(enemyAttackPower);

        if (user.isAlive()) {
            System.out.println("Player health " + user.getHealth());
        } else {
            System.out.println("User Killed. Game Over");
            return;
        }


    }

    public void defend() {

        int playerDefencePower = (int) (user.getDefencePower() * (RandomUtil.generateRandomInRange(1,10)/10));
        System.out.println("Player Defends");

        int enemyAttackPower = (int) ((enemy.getAttackPower() * (RandomUtil.generateRandomInRange(1,10)/10)) - playerDefencePower);

        if(enemyAttackPower<0){
            user.addExperience(2);
            System.out.println("Player Blocks Enemy attack completely");
        }
        else{
            System.out.println("Enemy Hits Player with " + enemyAttackPower);
            user.takeDamage(enemyAttackPower);
        }

        if (user.isAlive()) {
            System.out.println("Player health " + user.getHealth());
        } else {
            System.out.println("User Killed. Game Over");
            return;
        }

    }
}
