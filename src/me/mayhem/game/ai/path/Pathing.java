package me.mayhem.game.ai.path;

import me.mayhem.game.ai.path.impl.NoPathing;
import me.mayhem.game.entity.Entity;

/**
 * Pathing Interface
 * Generates a vector dependent on the current vector of an entity
 */
public interface Pathing {

    Pathing NO_PATHING = new NoPathing();

    /**
     * updatePosition takes an entity and updates its position
     * @param entity - Eg - An enemy
     */
    void updatePosition(Entity entity);



}
