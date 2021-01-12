package me.mayhem.game.collision.impl;

import me.mayhem.game.collision.AbstractHitbox;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.math.Vector;
import org.jsfml.graphics.FloatRect;

public class RectangleHitbox extends AbstractHitbox {

    protected FloatRect floatRect;

    public RectangleHitbox(Vector position, int height, int width) {
        super(1, position, height, width);

        this.floatRect = new FloatRect(position.getX(), position.getY(), height, width);
    }

    @Override
    public boolean collides(Hitbox other) {
        if (this.priority >= other.getPriority()) {
            return this.floatRect.contains(other.asVector().toVector());
        }

        return other.collides(this);
    }
}
