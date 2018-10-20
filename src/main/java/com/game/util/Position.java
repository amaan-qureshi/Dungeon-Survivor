package com.game.util;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.isNull;

public class Position implements Serializable{

    private final int x;

    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (isNull(anotherObject) || getClass() != anotherObject.getClass()) {
            return false;
        }

        Position anotherPosition = (Position) anotherObject;
        return x == anotherPosition.x && y == anotherPosition.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{x=" + x + ", y=" + y + '}';
    }
}
