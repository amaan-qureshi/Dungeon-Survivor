package com.game.components.maps;

import com.game.components.entities.Entity;
import com.game.constants.EntityType;
import com.game.util.Position;

import java.io.Serializable;

public class MapBlock implements Serializable{

    private Entity entity;
    private Position position;

    EntityType getBlockEntityType(){

        if(this.entity!=null) {
            return this.entity.getType();
        }

        return EntityType.UNKNOWN;
    }

    public Entity getEntity() {
        return entity;
    }

    void setEntity(Entity entity) {
        this.entity = entity;
    }

    Position getPosition() {
        return position;
    }

    void setPosition(Position position) {
        this.position = position;
    }
}
