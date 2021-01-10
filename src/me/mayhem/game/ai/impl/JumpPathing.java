package me.mayhem.game.ai.impl;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.ai.Pathing;

/**
 * Required to make an entity jump
 */
public class JumpPathing implements Pathing {


    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(0,1);
    }
}
