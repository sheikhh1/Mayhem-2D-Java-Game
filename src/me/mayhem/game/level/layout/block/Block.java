package me.mayhem.game.level.layout.block;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Block {

    private final Vector position;
    private final Sprite drawable;
    private final Hitbox hitbox;
    private final int width;
    private final int height;

    protected Block(Vector position, Sprite drawable, Hitbox hitbox, int width, int height) {
        this.position = position;
        this.drawable = drawable;
        this.hitbox = hitbox;
        this.width = width;
        this.height = height;
    }

    public void draw(RenderWindow renderWindow) {
        this.drawable.setPosition(this.position.toVector());
        renderWindow.draw(this.drawable);
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector getCenter() {
        return this.position.clone().add(this.width / 2f, this.height / 2f);
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public void onCollide(Entity entity) {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected Vector position;
        protected int width;
        protected int height;
        protected Sprite sprite;
        protected Hitbox hitbox;

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

        public Builder sprite(Sprite sprite) {
            this.sprite = sprite;
            return this;
        }

        public Block build() {
            if (this.hitbox == null) {
                this.hitbox = new SpriteHitbox(this.position, this.height, this.width);
            }

            return new Block(this.position, this.sprite, this.hitbox, this.width, this.height);
        }
    }
}
