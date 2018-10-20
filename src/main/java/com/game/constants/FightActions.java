package com.game.constants;

public enum FightActions {
    ATTACK("Attack Enemy"),
    DEFEND("Defend Enemy");

    private final String title;

    FightActions(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
