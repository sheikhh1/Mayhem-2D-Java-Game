package me.mayhem.entity.pathing;

import me.mayhem.entity.Entity;

/**
 * Required to make an entity jump
 */
public class JumpPathing implements Pathing{


    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(0,1);
    }
}
