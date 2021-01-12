package me.mayhem.game.entity.physics;

import me.mayhem.util.Vector;

public class EntityPhysics {

    private static final float GRAVITY = 1.5f;
    private static final float MAX_SPEED = 5f;

    private float jumpStrength = 20f;
    private float fallStrength = 0;
    private Vector motion;

    /**
     * Set Player motion
     * @param motion - Motion passed by player class
     */
    public void setEntityMotion(Vector motion) {
        this.motion = motion;
    }

    /**
     * Move Player Forward method by 5 Pixels
     */
    public void moveForward() {
        this.motion.add(MAX_SPEED,0);
    }

    /**
     * Move Player Back method by 5 Pixels
     */
    public void moveBack() {
        this.motion.subtract(MAX_SPEED,0);
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
}
