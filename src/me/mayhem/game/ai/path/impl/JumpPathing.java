package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.entity.Entity;

/**
 * Required to make an entity jump
 */
public class JumpPathing implements Pathing {


    @Override
    public void updatePosition(Entity entity) {
        entity.getMotion().add(0,1);
    }
}
