package com.game.maps;

import com.game.Entity;
import com.game.EntityType;
import com.game.Position;

import java.io.Serializable;

public class MapBlock implements Serializable{

    private Entity entity;
    private Position position;

    public EntityType getBlockEntityType(){

        if(this.entity!=null) {
            return this.entity.getType();
        }

        return EntityType.UNKNOWN;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
