package me.mayhem.entity.pathing.impl;

import me.mayhem.entity.Entity;
import me.mayhem.entity.pathing.Pathing;

/**
 * Required to make an entity move forward - (Positive X Axis)
 */
public class ForwardPathing implements Pathing {
    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(1,0);
    }
}
