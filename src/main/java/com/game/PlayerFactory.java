package com.game;

public class PlayerFactory {

    private final TextBox characterName = new TextBox("Character name:");

    private final Menu<GameCharacters> raceMenu = new Menu<>("Choose Player Kind:", GameCharacters.values());

    public Player getPlayer(){

        characterName.showTitle();
        String playerName = characterName.getValue();

        raceMenu.draw();
        GameCharacters character = raceMenu.chooseItem();

        return new Player(playerName,character.getHealth(),character.getAttackPower(),character.getDefencePower());
    }

}
