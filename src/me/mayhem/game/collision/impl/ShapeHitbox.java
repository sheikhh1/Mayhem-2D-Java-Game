package me.mayhem.game.collision.impl;

import me.mayhem.game.collision.AbstractHitbox;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Shape;

public class ShapeHitbox extends AbstractHitbox {

    private Shape shape;
    private Vector position;

    public ShapeHitbox(Shape shape, Vector position, int height, int width) {
        super(1,position, height, width);
        this.shape = shape;
        this.position = position;
    }

    @Override
    public boolean checkForCollision(Hitbox other) {
        return false;
    }

    @Override
    public FloatRect asFloatRect() {
        return new FloatRect(this.position.getX(), this.position.getY(), this.getHitboxWidth(), this.getHitboxHeight());
    }

    @Override
    public FloatRect getCollision(Hitbox other) {
        return null;
    }
}
