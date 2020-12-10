package me.mayhem.entity.sprites;

import me.mayhem.entity.Entity;
import me.mayhem.math.Vector;

/**
 * Player Class
 *
 */
public class Player extends Entity {

    public Player(String name, int health, Vector Position){
        // Need to sort out Entity Constructor

    }

    /**
     * Method to detect the player has not gone out of window bounds
     * If player hits window bounds -> Stop movement
     * @param Position - Takes the players position
     */
    public void boundsDetection(Vector Position){

    }

    /**
     * Draw Player to screen
     * Might not be needed
     */
    public void drawPlayer(){
        // drawing the player
    }
}
