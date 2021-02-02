package me.mayhem.game.entity.event;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.event.struct.Event;

public class EntityCollideEvent extends Event {

    private final Entity first;
    private final Entity second;

    public EntityCollideEvent(Entity first, Entity second) {
        this.first = first;
        this.second = second;
    }

    public Entity getFirst() {
        return this.first;
    }

    public Entity getSecond() {
        return this.second;
    }
}
