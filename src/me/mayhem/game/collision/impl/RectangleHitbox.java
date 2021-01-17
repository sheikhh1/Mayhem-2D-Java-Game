package me.mayhem.game.collision.impl;

import me.mayhem.game.collision.AbstractHitbox;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.FloatRect;

public class RectangleHitbox extends AbstractHitbox {

    protected FloatRect floatRect;

    public RectangleHitbox(Vector position, int height, int width, FloatRect floatRect) {
        super(1, position, height, width);
        this.floatRect = floatRect;
    }

    @Override
    public boolean checkForCollision(Hitbox other) {
        if (this.priority >= other.getPriority()) {
            return this.floatRect.intersection(other.asFloatRect()) != null;
        }

        return other.checkForCollision(this);
    }

    @Override
    public FloatRect asFloatRect() {
        return this.floatRect;
    }

    @Override
    public FloatRect getCollision(Hitbox other) {
        return null;
    }
}
