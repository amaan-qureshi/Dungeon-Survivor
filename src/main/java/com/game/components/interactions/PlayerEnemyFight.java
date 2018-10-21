package com.game.components.interactions;

import com.game.components.entities.Entity;
import com.game.components.input.impl.GenericMenuConsole;
import com.game.components.interactions.actions.AttackOperation;
import com.game.components.interactions.actions.DefendOperation;
import com.game.components.interactions.actions.PlayerActionInvoker;
import com.game.constants.FightActions;
import com.game.constants.MessageConstants;

import static com.game.messages.MessageUtil.getMessage;

public class PlayerEnemyFight implements Interaction {

    @SuppressWarnings("unchecked")
    private final GenericMenuConsole menuConsole = new GenericMenuConsole(getMessage(MessageConstants.CHARACTER_FIGHTING_OPTION), FightActions.values());

    @Override
    public void interact(Entity user, Entity enemy) {

        while (user.isAlive() && enemy.isAlive()) {
            switch ((FightActions) menuConsole.getActionValue()) {
                case ATTACK:
                    PlayerActionInvoker.executeOperation(new AttackOperation(user, enemy));
                    break;
                case DEFEND:
                    PlayerActionInvoker.executeOperation(new DefendOperation(user, enemy));
                    break;
            }
        }
    }
}
