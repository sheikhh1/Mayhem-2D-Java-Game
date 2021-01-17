package me.mayhem.game.collision.impl;

import me.mayhem.game.collision.AbstractHitbox;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Shape;

public class ShapeHitbox extends AbstractHitbox {

    private Shape shape;

    public ShapeHitbox(Shape shape, Vector position, int height, int width) {
        super(1, position, height, width);

        this.shape = shape;
    }

    @Override
    public boolean checkForCollision(Hitbox other) {
        return other.getCollision(this) != null;
    }

    @Override
    public FloatRect asFloatRect() {
        return this.shape.getGlobalBounds();
    }

    @Override
    public FloatRect getCollision(Hitbox other) {
        return other.asFloatRect().intersection(this.asFloatRect());
    }
}
