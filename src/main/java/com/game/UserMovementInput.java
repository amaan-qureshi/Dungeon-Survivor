package com.game;

import java.io.Serializable;

public interface UserMovementInput extends Serializable{
    Position getNextPosition(Position currentPosition);
}
