package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.type.BooleanAttribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;

public class MoveBetweenBlocksPathing implements Pathing {

    @Override
    public void updatePosition(Entity entity) {
        if (entity.getState(1) == EntityState.BACK) {
            entity.getMotion().add(-1, 0);
        } else {
            entity.getMotion().add(1, 0);
        }

        this.determineState(entity);
    }

    public void determineState(Entity entity) {
        BooleanAttribute collidedLeft = (BooleanAttribute) entity.getAttribute("collidedLeft", Boolean.class);
        BooleanAttribute collidedRight = (BooleanAttribute) entity.getAttribute("collidedRight", Boolean.class);

        if (collidedLeft != null && collidedLeft.getValue()) {
            entity.setState(EntityState.FORWARD);
            collidedLeft.setValue(false);
        } else if (collidedRight != null && collidedRight.getValue()) {
            entity.setState(EntityState.BACK);
            collidedRight.setValue(false);
        }
    }
}
