package com.game.components.input.impl;

import com.game.components.input.util.ActionMenu;
import com.game.components.input.Console;

public class GenericMenuConsole<T extends Enum> implements Console {

    private final ActionMenu<T> menu;

    @SafeVarargs
    public GenericMenuConsole(String menuTitle, T... menuActions) {
        menu = new ActionMenu<>(menuTitle, menuActions);
    }

    @Override
    public Enum getActionValue() {
        menu.render();
        return menu.chooseItem();
    }


}
