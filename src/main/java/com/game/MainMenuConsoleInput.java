package com.game;


public class MainMenuConsoleInput {

    private final Menu<MainMenuActions> menu = new Menu<>("Choose Option:", MainMenuActions.values());

    public MainMenuActions getAction() {
        menu.draw();
        return menu.chooseItem();
    }


}
