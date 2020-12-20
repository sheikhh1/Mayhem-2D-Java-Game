package me.mayhem.game.level.layout.block;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.math.Vector;
import org.jsfml.graphics.Drawable;

public class Block {

    private final Vector position;
    private final Drawable drawable;
    private final Hitbox hitbox;

    public Block(Vector position, Drawable drawable, Hitbox hitbox) {
        this.position = position;
        this.drawable = drawable;
        this.hitbox = hitbox;
    }

    public Vector getPosition() {
        return this.position;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }
}
