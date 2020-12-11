package me.mayhem.entity.pathing.impl;

import me.mayhem.entity.Entity;
import me.mayhem.entity.pathing.Pathing;

/**
 * NoPathing required for the player entity as AI is not needed
 */
public class NoPathing implements Pathing {

    @Override
    public void updatePosition(Entity entity) {
        // Do nothing for player
    }
}
