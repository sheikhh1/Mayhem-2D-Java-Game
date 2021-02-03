package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;

public class HoverPathing implements Pathing {

    private int updateFunctionCounter = 0;

    @Override
    public void updatePosition(Entity entity) {
        this.updateFunctionCounter++;

        if (this.updateFunctionCounter <= 50) {
            if (entity.getState(0) == EntityState.FALLING) {
                entity.setState(EntityState.NO_MOTION);
            }

            entity.setState(EntityState.JUMPING);
        } else {
            if (entity.getState(0) == EntityState.JUMPING) {
                entity.setState(EntityState.NO_MOTION);
            }

            entity.setState(EntityState.FALLING);
        }

        this.updateFunctionCounter = this.updateFunctionCounter % 100;
    }
}
