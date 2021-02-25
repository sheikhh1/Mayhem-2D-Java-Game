package me.mayhem.game.entity.event;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.event.struct.Event;

public class EntityDeathEvent extends Event {

    private final Entity entity;

    public EntityDeathEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }
}
