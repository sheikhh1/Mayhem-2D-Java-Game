package me.mayhem.game.ai.path.impl;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.type.BooleanAttribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.entities.projectile.EnemyProjectile;
import me.mayhem.game.entity.entities.projectile.ProjectileType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.UtilVector;
import org.jsfml.system.Clock;

public class RangedPathing implements Pathing {

    private final Level currentLevel;
    private Clock rateOfFire = new Clock();
    private int shootCounter = 0;

    public RangedPathing(Level currentLevel) {
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
                this.shootProjectile(entity);
            } else {
                entity.getAnimation().setRow(9);
                entity.setState(EntityState.STANDING);
                this.shootProjectile(entity);
            }

            return;
        }

        entity.getAnimation().setTimeOut(Integer.MAX_VALUE);
        this.determineState(entity);
    }

    public void shootProjectile(Entity entity) {
        EnemyProjectile fireBall;

        if (entity.isEntityGrounded()) {
            if (entity.getType() == EntityType.CORROSIVE) {
                if (this.rateOfFire.getElapsedTime().asMilliseconds() > 1000) {
                    fireBall = new EnemyProjectile(entity.getPosition().clone(), this.currentLevel.getPlayer().getPosition(), ProjectileType.WEAK);
                    this.currentLevel.spawnProjectile(fireBall);
                    this.rateOfFire.restart();
                }
            } else {
                if (this.rateOfFire.getElapsedTime().asMilliseconds() > 500) {
                    this.shootCounter++;

                    if(this.shootCounter <= 5) {
                        fireBall = new EnemyProjectile(entity.getPosition().clone(), this.currentLevel.getPlayer().getPosition(), ProjectileType.NORMAL);
                    } else {
                        fireBall = new EnemyProjectile(entity.getPosition().clone(), this.currentLevel.getPlayer().getPosition(), ProjectileType.SUPER);
                    }

                    if (this.shootCounter == 6) {
                        this.shootCounter = 1;
                    }
                    this.currentLevel.spawnProjectile(fireBall);
                    this.rateOfFire.restart();
                }
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
