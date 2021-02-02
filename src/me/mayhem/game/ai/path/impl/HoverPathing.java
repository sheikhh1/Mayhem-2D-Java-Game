package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;

public class HoverPathing implements Pathing {

    private int updateFunctionCounter = 0;

    @Override
    public void updatePosition(Entity entity) {
        updateFunctionCounter++;

        if (updateFunctionCounter <= 50) {
            entity.setJumping(true);
            entity.setFalling(false);
        } else {
            entity.setJumping(false);
            entity.setFalling(true);
        }

        if (updateFunctionCounter == 100) updateFunctionCounter = 0;

    }
}
