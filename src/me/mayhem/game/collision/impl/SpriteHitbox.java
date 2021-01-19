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
    public FloatRect getCollision(Hitbox other) {
        return this.asFloatRect().intersection(other.asFloatRect());
    }

    @Override
    public FloatRect asFloatRect() {
        return new FloatRect(this.position.getX(), this.position.getY(), this.width, this.height);
    }
}
