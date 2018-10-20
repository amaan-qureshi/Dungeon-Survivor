package com.game.entities;

import com.game.constants.GameCharacters;
import com.game.input.TextBox;
import com.game.input.ActionMenu;

public class PlayerFactory {

    private static final TextBox characterName = new TextBox("Character name:");
    private static final ActionMenu<GameCharacters> raceMenu = new ActionMenu<>("Choose Player Kind:", GameCharacters.values());

    public static Player getPlayer() {
        characterName.showTitle();
        String playerName = characterName.getValue();
        raceMenu.draw();
        GameCharacters character = raceMenu.chooseItem();
        return new Player(playerName, character.getHealth(), character.getAttackPower(), character.getDefencePower());
    }

}
