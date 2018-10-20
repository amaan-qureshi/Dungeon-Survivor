package com.game;

import java.io.Serializable;

public class MapBlock implements Serializable{

    private boolean containsLiveEntity;
    private Entity entity;
    private Position position;

    public EntityType getBlockEntityType(){

        if(this.entity!=null) {
            return this.entity.getType();
        }

        return EntityType.UNKNOWN;
    }

    public boolean isContainsLiveEntity() {
        return containsLiveEntity;
    }

    public void setContainsLiveEntity(boolean containsLiveEntity) {
        this.containsLiveEntity = containsLiveEntity;
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
