package me.mayhem.entity.hitbox;


import me.mayhem.math.Vector;

/**
 *
 *
 *
 */
public interface Hitbox {

    int getPriority();

    boolean collides(Hitbox other);

    Vector asVector();

}
