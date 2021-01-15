package me.mayhem.game.collision.impl;

import me.mayhem.game.collision.AbstractHitbox;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;

public class SpriteHitbox extends AbstractHitbox {

    private Sprite sprite;

    public SpriteHitbox(Sprite sprite, Vector position, int height, int width) {
        super(1, position, height, width);
        this.sprite = sprite;

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
