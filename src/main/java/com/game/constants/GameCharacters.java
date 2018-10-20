package com.game.constants;
public enum GameCharacters {
    SOLDIER("Knight","Elite Balanced Fighter",30,30,40),
    NINJA("Ninja","Silent and Fast",50,25,25),
    TANK("Tank","Slow and Strong",30,20,50);

    private final String playerClass;
    private final String description;
    private final int health;
    private final int attackPower;
    private final int defencePower;

    GameCharacters(String playerClass,String description,int health,int attackPower,int defencePower) {
        this.playerClass = playerClass;
        this.description = description;
        this.health = health;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    @Override
    public String toString() {
        return "Class=>"+playerClass+"  Description=>"+description+"  HP=>"+health+"  POWER=>"+attackPower+"  ARMOUR=>"+defencePower;
    }
}
