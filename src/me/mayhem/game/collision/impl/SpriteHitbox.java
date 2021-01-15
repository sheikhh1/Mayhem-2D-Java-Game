package me.mayhem.game.collision.impl;

import me.mayhem.game.collision.AbstractHitbox;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;

public class SpriteHitbox extends AbstractHitbox {

    public SpriteHitbox(Sprite sprite, Vector position, int height, int width) {
        super(1, position, height, width);

    }

    @Override
    public boolean checkForCollision(Hitbox other) {
        return false;
    }

    @Override
    public FloatRect asFloatRect() {
        return null;
    }
}
