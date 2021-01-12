package me.mayhem.game.entity.player.physics;

import me.mayhem.util.math.Vector;

public class PlayerPhysics {

    private static final float GRAVITY = 1.5f;
    private static final float MAX_SPEED = 5f;

    private float jumpStrength = 20f;
    private float fallStrength = 0;
    private Vector currentPosition;

    private float yBound = 595f; // TODO: Update from collision Detection

    /**
     * Takes current player position from Player Class
     * @param currentPosition - Player Position
     */
    public void setPlayerPosition(Vector currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Move Player Forward method by 5 Pixels
     */
    public void moveForward() {
        this.currentPosition.add(MAX_SPEED,0);
    }

    /**
     * Move Player Back method by 5 Pixels
     */
    public void moveBack() {
        this.currentPosition.subtract(MAX_SPEED,0);
    }

    /**
     * Jump method for the Player
     */
    public void jump() {
        this.currentPosition.subtract(0, this.jumpStrength);
        this.jumpStrength -= GRAVITY;
    }

    /**
     * Basic Fall Method Added
     */
    public void fall() {
        this.currentPosition.add(0, fallStrength);
        this.fallStrength += GRAVITY;
    }

    /**
     * Test Collision Checker
     * @return true if player has collided with floor, otherwise return false
     */
    public boolean checkCollision() {
        if (this.currentPosition.getY() >= this.yBound) {
            this.currentPosition.set(this.currentPosition.getX(), this.yBound);
            this.jumpStrength = 20f;
            return true;
        }

        return false;
    }
}
