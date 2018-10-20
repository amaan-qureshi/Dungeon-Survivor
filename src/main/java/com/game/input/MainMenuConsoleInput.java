package com.game.input;


import com.game.constants.MainMenuActions;

public class MainMenuConsoleInput {

    private final ActionMenu<MainMenuActions> menu = new ActionMenu<>("Choose Option:", MainMenuActions.values());

    public MainMenuActions getAction() {
        menu.draw();
        return menu.chooseItem();
    }


}
