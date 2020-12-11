package me.mayhem.entity.hitbox;


/**
 *
 *
 *
 */
public interface Hitbox {

    boolean collides(Hitbox other);

    void handleCollision(Hitbox other);

}
