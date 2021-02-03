package me.mayhem.game.entity.player.listeners.game;

import me.mayhem.game.entity.enemies.Enemy;
import me.mayhem.game.entity.event.PlayerCollideWithEntityEvent;
import me.mayhem.game.event.struct.EventListener;

public class PlayerEnemyCollideListener {

    @EventListener
    public void onPlayerCollideWithEnemy(PlayerCollideWithEntityEvent event) {
        if (event.getEntity() instanceof Enemy) {
            ((Enemy) event.getEntity()).attack(event.getPlayer());
        }
    }
}
