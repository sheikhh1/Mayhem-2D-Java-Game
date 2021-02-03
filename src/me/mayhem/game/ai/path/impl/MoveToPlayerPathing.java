package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.type.BooleanAttribute;
import me.mayhem.game.attribute.type.LongAttribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;

public class MoveToPlayerPathing implements Pathing {

    private Level currentLevel;

    public MoveToPlayerPathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {
        if (this.shouldStopMoving(entity)) {
            entity.setState(EntityState.STANDING);
            return;
        }

        Vector toPlayer = currentLevel.getPlayer().getPosition().clone().subtract(entity.getPosition());
        toPlayer.setY(0);
        toPlayer.normalize();
        entity.getMotion().add(toPlayer);

        this.attemptJumpOverBlocks(entity);
        this.determineState(entity, toPlayer);
    }

    private boolean shouldStopMoving(Entity entity) {
        LongAttribute stopMotion = (LongAttribute) entity.getAttribute("stopMotion", Long.class);

        if (stopMotion == null) {
            return false;
        }

        return System.currentTimeMillis() < stopMotion.getValue();
    }

    private void attemptJumpOverBlocks(Entity entity) {
        BooleanAttribute collided = (BooleanAttribute) entity.getAttribute("collided", Boolean.class);

        if (collided == null || !collided.getValue()) {
            return;
        }

        entity.setState(EntityState.JUMPING);
        collided.setValue(false);
    }

    private void determineState(Entity entity, Vector toPlayer) {
        if (toPlayer.getX() < 0 ) {
            entity.setState(EntityState.BACK);
        } else if (toPlayer.getX() > 0){
            entity.setState(EntityState.FORWARD);
        } else if (toPlayer.getX() == 0){
            entity.setState(EntityState.STANDING);
        }
    }


}
