package me.mayhem.game.entity.player.listeners.game;

import me.mayhem.game.entity.event.PlayerCollideWithEntityEvent;
import me.mayhem.game.event.struct.EventListener;

import java.util.concurrent.TimeUnit;

public class PlayerEnemyCollideListener {

    @EventListener
    public void onPlayerCollideWithEnemy(PlayerCollideWithEntityEvent event) {
        event.getPlayer().getMotion().add(event.getEntity().getFacing().clone().add(0, -1).multiply(4));
        event.getEntity().getMotion().add(event.getPlayer().getFacing().clone().multiply(5));
        event.getEntity().setAttribute("stopMotion", System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10));
    }

}
