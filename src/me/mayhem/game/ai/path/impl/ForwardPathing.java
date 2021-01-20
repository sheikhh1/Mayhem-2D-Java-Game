package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;

/**
 * Required to make an entity move forward - (Positive X Axis)
 */
public class ForwardPathing implements Pathing {
    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(1,0);
    }
}
