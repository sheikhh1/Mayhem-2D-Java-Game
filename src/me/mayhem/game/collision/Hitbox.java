package me.mayhem.game.collision;


import me.mayhem.util.math.Vector;

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
    boolean collides(Hitbox other);

    /**
     * returns the entities position as a vector
     * @return the vector of the entity
     */
    Vector asVector();

}
