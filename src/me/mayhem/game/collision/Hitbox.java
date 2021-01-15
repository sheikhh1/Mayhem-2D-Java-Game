package me.mayhem.game.collision;


import me.mayhem.util.Vector;
import org.jsfml.graphics.FloatRect;

/**
 *
 *  an interface to define the hitbox for an entity
 *
 */
public interface Hitbox {
    /**
     *
     * @return the priority of the hitbox
     */
    int getPriority();

    /**
     *
     * @param other the hitbox for the other entity
     * @return true if the two hitboxes collided
     */
    boolean checkForCollision(Hitbox other);

    /**
     * returns the entities position as a vector
     * @return the vector of the entity
     */
    Vector asVector();

    FloatRect asFloatRect();

}
