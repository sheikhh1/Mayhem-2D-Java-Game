package me.mayhem.game.entity.player;

import me.mayhem.game.entity.Entity;

/**
 * Interface created for player to attack enemies and interact with doors / keycards
 */
public interface PlayerInteract {

    /**
     * Attack an enemy
     * @param enemy
     */
    void attack(Entity enemy);

}
