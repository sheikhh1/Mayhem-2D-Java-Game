package me.mayhem.game.entity.entities.collect;

import me.mayhem.game.entity.player.Player;

public interface Collectable {

    /**
     *
     * Called when the entity collides with the {@link Player}
     *
     * @param player The {@link Player} collecting the {@link Collectable}
     */
    void collected(Player player);

}
