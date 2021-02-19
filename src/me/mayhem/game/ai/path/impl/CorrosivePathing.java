package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.type.BooleanAttribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.UtilVector;

public class CorrosivePathing implements Pathing {

    private final Level currentLevel;

    public CorrosivePathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {
        if (UtilVector.getDistanceSquared(entity.getPosition(), this.currentLevel.getPlayer().getPosition()) <= 60000) {
            Vector toPlayer = entity.getPosition().clone().subtract(this.currentLevel.getPlayer().getPosition()).normalize();

            if (toPlayer.getX() == 1) {
                entity.setState(EntityState.FORWARD);
                entity.getAnimation().setTimeOut(1);
            } else {
                entity.setState(EntityState.BACK);
                entity.getAnimation().setTimeOut(1);
            }

            return;
        }

        entity.getAnimation().setTimeOut(Integer.MAX_VALUE);
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
        } else {
            entity.setState(EntityState.FORWARD);
        }
    }
}
