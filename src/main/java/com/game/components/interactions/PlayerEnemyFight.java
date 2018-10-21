package com.game.components.interactions;

import com.game.constants.FightActions;
import com.game.components.input.impl.GenericMenuConsole;
import com.game.constants.MessageConstants;
import com.game.util.RandomUtil;
import com.game.components.entities.Entity;
import static com.game.messages.MessageUtil.getMessage;

public class PlayerEnemyFight implements Interaction {

    private Entity user;
    private Entity enemy;
    private static final String DEFAULT_SPACER = " ";

    @SuppressWarnings("unchecked")
    private final GenericMenuConsole menuConsole = new GenericMenuConsole(getMessage(MessageConstants.CHARACTER_FIGHTING_OPTION), FightActions.values());

    @Override
    public void interact(Entity user, Entity enemy) {
        this.user = user;
        this.enemy = enemy;

        while(user.isAlive() && enemy.isAlive()) {
            switch ((FightActions)menuConsole.getActionValue()) {
                case ATTACK:
                    attackEnemy();
                    break;
                case DEFEND:
                    defend();
                    break;
            }
        }
    }

    private void attackEnemy() {
        int playerAttackPower = (int) (user.getAttackPower() * (RandomUtil.generateRandomInRange(1,10)/10));
        System.out.print(getMessage(MessageConstants.DEAL_ENEMY_DAMAGE,user.getPlayerClass(),user.getName(),playerAttackPower)+DEFAULT_SPACER);
        enemy.takeDamage(playerAttackPower);
        if (enemy.isAlive()) {
            System.out.print(getMessage(MessageConstants.ENEMY_HEALTH_STATUS,enemy.getHealth())+DEFAULT_SPACER);
        } else {
            user.addExperience(10);
            System.out.println(getMessage(MessageConstants.ENEMY_KILLED)+DEFAULT_SPACER);
            return;
        }
        System.out.println();

        int enemyAttackPower = (int) (enemy.getAttackPower() * (RandomUtil.generateRandomInRange(1,10)/10));
        System.out.print(getMessage(MessageConstants.DEAL_PLAYER_DAMAGE,enemyAttackPower)+DEFAULT_SPACER);
        user.takeDamage(enemyAttackPower);

        if (user.isAlive()) {
            System.out.print(getMessage(MessageConstants.PLAYER_HEALTH_STATUS,user.getPlayerClass(),user.getName(), user.getHealth())+DEFAULT_SPACER);
        } else {
            System.out.print(getMessage(MessageConstants.GAME_OVER,user.getPlayerClass(),user.getName())+DEFAULT_SPACER);
        }
        System.out.println();
    }

    private void defend() {

        int playerDefencePower = (int) (user.getDefencePower() * (RandomUtil.generateRandomInRange(1,10)/10));
        System.out.print(getMessage(MessageConstants.SHIELD_UP,user.getPlayerClass(),user.getName())+DEFAULT_SPACER);

        int enemyAttackPower = (int) ((enemy.getAttackPower() * (RandomUtil.generateRandomInRange(1,10)/10)) - playerDefencePower);

        if(enemyAttackPower<0){
            user.addExperience(2);
            System.out.print(getMessage(MessageConstants.SHIELD_ABSORBS_ALL_DAMAGE)+DEFAULT_SPACER);
        }
        else{
            System.out.print( getMessage(MessageConstants.DEAL_PLAYER_DAMAGE,enemyAttackPower)+DEFAULT_SPACER);
            user.takeDamage(enemyAttackPower);
        }
        System.out.println();

        if (user.isAlive()) {
            System.out.println(getMessage(MessageConstants.PLAYER_HEALTH_STATUS,user.getPlayerClass(),user.getName(), user.getHealth())+DEFAULT_SPACER);
        } else {
            System.out.println(getMessage(MessageConstants.GAME_OVER,user.getPlayerClass(),user.getName())+DEFAULT_SPACER);
        }

    }
}
