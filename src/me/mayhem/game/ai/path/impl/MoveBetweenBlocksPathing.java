package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.type.BooleanAttribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.state.EntityState;

public class MoveBetweenBlocksPathing implements Pathing {


    @Override
    public void updatePosition(Entity entity) {

        if (entity.getState(1) == EntityState.BACK) {
            entity.getMotion().add( - EntityPhysics.MAX_SPEED,0);
        } else {
            entity.getMotion().add(EntityPhysics.MAX_SPEED,0);

        }
        //this.determineState(entity);
    }


}
