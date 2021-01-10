package me.mayhem.game.ai.impl;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.ai.Pathing;

/**
 * Required to make an entity move forward - (Positive X Axis)
 */
public class ForwardPathing implements Pathing {
    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(1,0);
    }
}
