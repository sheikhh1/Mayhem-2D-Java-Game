package me.mayhem.game.entity.player.physics;

import me.mayhem.util.Vector;

public class PlayerPhysics {

    private static final float GRAVITY = 0.098f;
    private static final float MAX_SPEED = 2f;

    private float jumpStrength = 2f;
    private float fallStrength = 0;
    private Vector playerMotion;

    /**
     * Set Player motion
     * @param motion - Motion passed by player class
     */
    public void setPlayerMotion(Vector motion) {
        this.playerMotion = motion;
    }

    /**
     * Move Player Forward method by 5 Pixels
     */
    public void moveForward() {
        this.playerMotion.add(MAX_SPEED,0);
    }

    /**
     * Move Player Back method by 5 Pixels
     */
    public void moveBack() {
        this.playerMotion.subtract(MAX_SPEED,0);
    }

    /**
     * Jump method for the Player
     */
    public void jump() {
        this.playerMotion.subtract(0, this.jumpStrength);
        this.jumpStrength -= GRAVITY;
    }

    /**
     * Basic Fall Method Added
     */
    public void fall() {
        this.playerMotion.add(0, this.fallStrength);
        this.fallStrength += GRAVITY;
    }
}
