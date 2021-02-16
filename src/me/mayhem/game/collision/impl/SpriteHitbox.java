package me.mayhem.game.collision.impl;

import me.mayhem.game.collision.AbstractHitbox;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.FloatRect;

public class SpriteHitbox extends AbstractHitbox {

    public SpriteHitbox(Vector position, int height, int width) {
        super(1, position, height, width);
    }

    @Override
    public boolean checkForCollision(Hitbox other) {
        return this.getCollision(other) != null;
    }

    @Override
    public Vector getCollision(Hitbox other) {
        FloatRect collision = other.asFloatRect().intersection(this.asFloatRect());

        if (collision == null) {
            return null;
        }

        return new Vector(collision.left, collision.top + collision.height / 2);
    }

    @Override
    public FloatRect asFloatRect() {
        return new FloatRect(this.position.getX(), this.position.getY(), this.width, this.height);
    }

    @Override
    public String toString() {
        return asFloatRect().toString();
    }
}
