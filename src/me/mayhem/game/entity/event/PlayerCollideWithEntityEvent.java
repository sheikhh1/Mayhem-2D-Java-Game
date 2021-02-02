package me.mayhem.game.entity.event;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.event.struct.Event;

public class PlayerCollideWithEntityEvent extends Event {

    private final Player player;
    private final Entity entity;

    public PlayerCollideWithEntityEvent(Player player, Entity entity) {
        this.player = player;
        this.entity = entity;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Entity getEntity() {
        return this.entity;
    }
}
