package com.game;

public class UserFightConsoleInput {

    private final Menu<FightActions> menu = new Menu<>("Choose character action:", FightActions.values());

    public FightActions getAction() {
        menu.draw();
        return menu.chooseItem();
/*        switch (item) {
            case UP:
                return Position.of(currentPosition.getX()-1, currentPosition.getY());
            case DOWN:
                return Position.of(currentPosition.getX()+1, currentPosition.getY());
            case RIGHT:
                return Position.of(currentPosition.getX(), currentPosition.getY()+1);
            case LEFT:
                return Position.of(currentPosition.getX(), currentPosition.getY()-1);
            default:
                throw new IllegalStateException("Unsupported menu item: " + item);
        }*/
    }

}
