package me.mayhem.entity.sprites.player.physics;

import me.mayhem.math.Vector;

public class PlayerPhysics {

    private float GRAVITY = 1.5f;
    private float MAX_SPEED = 5f;
    private float JUMP_STRENGTH = 20f;
    private Vector currentPosition;

    /**
     * Takes current player position from Player Class
     * @param currentPosition - Player Position
     */
    public void setPlayerPosition(Vector currentPosition){this.currentPosition=currentPosition;}


}
