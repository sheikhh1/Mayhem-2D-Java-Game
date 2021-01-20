package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.attribute.type.BooleanAttribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.physics.EntityPhysics;
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
        Vector toPlayer = currentLevel.getPlayer().getPosition().clone().subtract(entity.getPosition());
        toPlayer.setY(0);
        toPlayer.normalize().multiply(EntityPhysics.MAX_SPEED);
        entity.getMotion().add(toPlayer);

        Attribute<?> collided = entity.getAttribute("collided");

        if ((collided instanceof BooleanAttribute)) {
            if (((BooleanAttribute) collided).getValue()) {
                entity.setState(EntityState.JUMPING);
                ((BooleanAttribute) collided).setValue(false);
            }
        }

        this.determineState(entity, toPlayer);
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
