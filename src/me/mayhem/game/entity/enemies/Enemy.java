package me.mayhem.game.entity.enemies;

import me.mayhem.game.entity.player.Player;

/**
 *
 * Enemy interface to handle the entity attacking the player
 *
 */
public interface Enemy {

    /**
     *
     * Called when the entity collides with the {@link Player}
     *
     * @param player The {@link Player} being attacked
     */
    void attack(Player player);

}
