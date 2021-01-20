package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;

/**
 * NoPathing required for the player entity as AI is not needed
 */
public class NoPathing implements Pathing {

    @Override
    public void updatePosition(Entity entity) {
        // Do nothing for player
    }
}
