package me.mayhem.game.level.layout.block;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.RectangleHitbox;
import me.mayhem.math.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;

public class Block {

    private final Vector position;
    private final Drawable drawable;
    private final Hitbox hitbox;
    private final Color color;

    protected Block(Vector position, Drawable drawable, Hitbox hitbox, Color color) {
        this.position = position;
        this.drawable = drawable;
        this.hitbox = hitbox;
        this.color = color;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Vector position;
        private int width;
        private int height;
        private Drawable drawable;
        private Hitbox hitbox;
        private Color color;

        protected Builder() {}

        public Builder position(Vector position) {
            this.position = position;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder drawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder hitbox(Hitbox hitbox) {
            this.hitbox = hitbox;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Block build() {
            if (this.hitbox == null) {
                this.hitbox = new RectangleHitbox(this.position, this.height, this.width);
            }

            return new Block(this.position, this.drawable, this.hitbox, this.color);
        }
    }
}
