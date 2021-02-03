package me.mayhem.game.entity.player.listeners.game;

import me.mayhem.game.entity.event.PlayerCollideWithEntityEvent;
import me.mayhem.game.event.struct.EventListener;

public class PlayerEnemyCollideListener {

    @EventListener
    public void onPlayerCollideWithEnemy(PlayerCollideWithEntityEvent event) {
        if (event.getEntity().getFacing().normalize().equals(event.getPlayer().getFacing().normalize())) {
            event.getPlayer().getMotion().add(event.getPlayer().getFacing().clone().add(0, -1).multiply(4));
            event.getEntity().getMotion().add(event.getEntity().getFacing().clone().multiply(-1).multiply(5));
        } else {
            event.getPlayer().getMotion().add(event.getEntity().getFacing().clone().add(0, -1).multiply(4));
            event.getEntity().getMotion().add(event.getPlayer().getFacing().clone().multiply(5));
        }
    }

}
