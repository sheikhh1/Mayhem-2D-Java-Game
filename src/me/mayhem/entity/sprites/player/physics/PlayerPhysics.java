package me.mayhem.entity.sprites.player.physics;

import me.mayhem.math.Vector;

public class PlayerPhysics {

    private float GRAVITY = 1.5f;
    private float MAX_SPEED = 5f;
    private float JUMP_STRENGTH = 20f;
    private Vector currentPosition;
    private float FALL_STRENGTH = 0;

    private float yBound = 430f; // Floor Height = GetYPosition - Rectangle.size

    /**
     * Takes current player position from Player Class
     * @param currentPosition - Player Position
     */
    public void setPlayerPosition(Vector currentPosition){this.currentPosition=currentPosition;}

    /**
     * Move Player Forward method by 5 Pixels
     */
    public void moveForward(){currentPosition.add(MAX_SPEED,0);}

    /**
     * Move Player Back method by 5 Pixels
     */
    public void moveBack(){currentPosition.subtract(MAX_SPEED,0);}

    /**
     * Jump method for the Player
     */
    public void jump(){
        currentPosition.subtract(0,JUMP_STRENGTH);
        JUMP_STRENGTH -= GRAVITY;
    }

    /**
     * Basic Fall Method Added
     */
    public void fall(){
        currentPosition.add(0,FALL_STRENGTH);
        FALL_STRENGTH += GRAVITY;
    }

    /**
     * Test Collision Checker
     * @return true if player has collided with floor, otherwise return false
     */
    public boolean checkCollision(){
        if (currentPosition.getY() >= yBound){
            currentPosition.set(currentPosition.getX(),yBound);
            JUMP_STRENGTH = 20f;
            return true;
        }

        return false;
    }
}
