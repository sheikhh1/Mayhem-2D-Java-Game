package me.mayhem.game.ai.impl;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.ai.Pathing;

/**
 * NoPathing required for the player entity as AI is not needed
 */
public class NoPathing implements Pathing {

    @Override
    public void updatePosition(Entity entity) {
        // Do nothing for player
    }
}
