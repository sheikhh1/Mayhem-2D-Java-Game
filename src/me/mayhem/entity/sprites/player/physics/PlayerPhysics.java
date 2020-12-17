package me.mayhem.entity.sprites.player.physics;

import me.mayhem.math.Vector;

public class PlayerPhysics {

    private Vector currentPosition;

    /**
     * Takes current player position from Player Class
     * @param currentPosition - Player Position
     */
    public void setPlayerPosition(Vector currentPosition){this.currentPosition=currentPosition;}
}
