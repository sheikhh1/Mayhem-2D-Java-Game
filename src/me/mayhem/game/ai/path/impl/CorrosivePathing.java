package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.type.BooleanAttribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.entities.projectile.Projectile;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.UtilVector;
import org.jsfml.system.Clock;

public class CorrosivePathing implements Pathing {

    private final Level currentLevel;
    private Clock rateOfFire = new Clock();

    public CorrosivePathing(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void updatePosition(Entity entity) {
        if (entity.isMelee() || entity.isDead()) {
            return;
        }

        if (UtilVector.getDistanceSquared(entity.getPosition(), this.currentLevel.getPlayer().getPosition()) <= 60000) {
            Vector toPlayer = entity.getPosition().clone().subtract(this.currentLevel.getPlayer().getPosition()).normalize();

            if (toPlayer.getX() < 0) {
                entity.getAnimation().setRow(11);
                entity.setState(EntityState.STANDING);
            } else {
                entity.getAnimation().setRow(9);
                entity.setState(EntityState.STANDING);
            }

            return;
        }

        entity.getAnimation().setTimeOut(Integer.MAX_VALUE);
        this.determineState(entity);
    }

    public void shootProjectile(Entity entity) {
        Projectile fireBall;

        if (entity.isEntityGrounded()) {
            if (this.rateOfFire.getElapsedTime().asMilliseconds() > 1000) {
                fireBall = new Projectile(entity.getPosition().clone(), this.currentLevel.getPlayer().getPosition());
                this.currentLevel.spawnProjectile(fireBall);
                this.rateOfFire.restart();
            }
        }
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

            if (entity.getState(1) == EntityState.STANDING) {
                entity.setState(EntityState.FORWARD);
            }
        }
    }
}
