package me.mayhem.entity.pathing.impl;

import me.mayhem.entity.Entity;
import me.mayhem.entity.pathing.Pathing;

/**
 * Required to make an entity jump
 */
public class JumpPathing implements Pathing {


    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(0,1);
    }
}
