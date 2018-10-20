package com.game.input;

import com.game.util.Position;

import java.io.Serializable;

public interface UserMovementInput extends Serializable{
    Position getNextPosition(Position currentPosition);
}
