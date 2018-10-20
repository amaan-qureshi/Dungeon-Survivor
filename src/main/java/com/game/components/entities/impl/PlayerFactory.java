package com.game.components.entities.impl;

import com.game.constants.GameCharacters;
import com.game.components.input.impl.GenericMenuConsole;
import com.game.components.input.util.TextBox;

import static com.game.messages.MessageUtil.getMessage;

public class PlayerFactory {

    private static final TextBox characterName = new TextBox(getMessage("CHARACTER_NAME_OPTION"));

    @SuppressWarnings("unchecked")
    private static final GenericMenuConsole raceMenu = new GenericMenuConsole(getMessage("PLAYER_CLASS_OPTION"), GameCharacters.values());

    public static Player getPlayer() {
        characterName.showTitle();
        String playerName = characterName.getValue();
        GameCharacters character = (GameCharacters) raceMenu.getActionValue();
        return new Player(playerName,character.getPlayerClass(), character.getHealth(), character.getAttackPower(), character.getDefencePower());
    }

}
