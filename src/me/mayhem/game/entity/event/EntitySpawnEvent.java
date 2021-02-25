package me.mayhem.game.entity.event;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.event.struct.Event;
import me.mayhem.game.level.Level;

public class EntitySpawnEvent extends Event {

    private final Entity entity;
    private final Level level;

    public EntitySpawnEvent(Entity entity, Level level) {
        this.entity = entity;
        this.level = level;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public Level getLevel() {
        return this.level;
    }
}
