package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;

/**
 * Required to make an entity move forward - (Positive X Axis)
 */
public class ForwardPathing implements Pathing {
    @Override
    public void updatePosition(Entity entity) {
        if (entity.getState(1) != EntityState.FORWARD) {
            entity.setState(EntityState.FORWARD);
        }
    }
}
