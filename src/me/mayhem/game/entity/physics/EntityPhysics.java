package me.mayhem.game.entity.physics;

import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;

public class EntityPhysics {

    public static final float GRAVITY = 0.196f;
    public static final float MAX_SPEED = 8f;
    public static final float MAX_FALL_SPEED = 5f;

    private float jumpStrength;
    private float fallStrength = 0;

    protected EntityType entityType;
    protected Vector motion;


    public EntityPhysics(EntityType entityType, Vector motion) {
        this.entityType = entityType;
        this.motion = motion;
        this.jumpStrength = entityType.getJumpStrength();
    }

    /**
     * Move Player Forward method by 5 Pixels
     */
    public void moveForward() {
        this.motion.add(this.entityType.getMovementSpeed(),0);
    }

    /**
     * Move Player Back method by 5 Pixels
     */
    public void moveBack() {
        this.motion.subtract(this.entityType.getMovementSpeed(),0);
    }

    /**
     * Jump method for the Player
     */
    public void jump() {
        this.motion.subtract(0, this.jumpStrength);
        this.jumpStrength -= GRAVITY;
    }

    /**
     * Basic Fall Method Added
     */
    public void fall() {
        this.motion.add(0, this.fallStrength);
        this.fallStrength += GRAVITY;
    }

    /**
     * Set Jump Strength 
     * @param jumpStrength
     */
    public void setJumpStrength(float jumpStrength) {
        this.jumpStrength = jumpStrength;
    }

    /**
     * Set Fall Strength
     * @param fallStrength
     */
    public void setFallStrength(float fallStrength) {
        this.fallStrength = fallStrength;
    }

    public void reset(EntityState state) {
        if (state.getIndex() == 0) {
            this.jumpStrength = DEFAULT_JUMP_STRENGTH;
            this.fallStrength = 0;
            this.motion.setY(0);
        } else {
            this.motion.setX(0);
        }
    }

    public float getFallStrength() {
        return this.fallStrength;
    }
}
