package me.mayhem.game.entity.event.impl;

import me.mayhem.game.entity.event.EntityCollideEvent;
import me.mayhem.game.entity.event.PlayerCollideWithEntityEvent;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.event.struct.EventListener;

public class PlayerCollisionListener {

    @EventListener
    public void onEntityCollideWithPlayer(EntityCollideEvent event) {
        if (event.getFirst() instanceof Player) {
            EventManager.callEvent(new PlayerCollideWithEntityEvent((Player) event.getFirst(), event.getSecond()));
        } else if (event.getSecond() instanceof Player) {
            EventManager.callEvent(new PlayerCollideWithEntityEvent((Player) event.getSecond(), event.getFirst()));
        }
    }

}
