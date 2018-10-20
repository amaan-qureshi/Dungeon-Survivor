package com.game.input;

import com.game.constants.FightActions;

public class UserFightConsoleInput {

    private final ActionMenu<FightActions> menu = new ActionMenu<>("Choose character action:", FightActions.values());

    public FightActions getAction() {
        menu.draw();
        return menu.chooseItem();
    }

}
