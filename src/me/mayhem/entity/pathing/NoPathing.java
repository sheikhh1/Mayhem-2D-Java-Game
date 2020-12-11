package me.mayhem.entity.pathing;

import me.mayhem.entity.Entity;

/**
 * NoPathing required for the player entity as AI is not needed
 */
public class NoPathing implements Pathing {

    @Override
    public void updatePosition(Entity entity) {
        // Do nothing for player
    }
}
