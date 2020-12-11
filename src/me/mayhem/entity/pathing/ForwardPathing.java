package me.mayhem.entity.pathing;

import me.mayhem.entity.Entity;

/**
 * Required to make an entity move forward - (Positive X Axis)
 */
public class ForwardPathing implements Pathing {
    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(1,0);
    }
}
