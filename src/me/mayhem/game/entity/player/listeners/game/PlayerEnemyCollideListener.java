package me.mayhem.game.entity.player.listeners.game;

import me.mayhem.game.entity.entities.collect.Collectable;
import me.mayhem.game.entity.entities.enemies.Enemy;
import me.mayhem.game.entity.event.PlayerCollideWithEntityEvent;
import me.mayhem.game.event.struct.EventListener;

public class PlayerEnemyCollideListener {

    @EventListener
    public void onPlayerCollideWithEnemy(PlayerCollideWithEntityEvent event) {
        if (event.getEntity() instanceof Enemy) {
            ((Enemy) event.getEntity()).attack(event.getPlayer());
        } else if (event.getEntity() instanceof Collectable) {
            ((Collectable) event.getEntity()).collected(event.getPlayer());
        }
    }
}
