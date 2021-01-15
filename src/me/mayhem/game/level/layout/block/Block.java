package me.mayhem.game.level.layout.block;

import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.RectangleHitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;

public class Block {

    private final Vector position;
    private final Shape drawable;
    private final Hitbox hitbox;

    protected Block(Vector position, Shape drawable, Hitbox hitbox) {
        this.position = position;
        this.drawable = drawable;
        this.hitbox = hitbox;
    }

    public void draw(RenderWindow renderWindow) {
        this.drawable.setPosition(this.position.toVector());
        renderWindow.draw(this.drawable);
    }

    public Vector getPosition() {
        return this.position;
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
        private Shape drawable;
        private Hitbox hitbox;
        private Color outlineColor = Color.WHITE;
        private Color fillColor = Color.WHITE;

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

        public Builder drawable(Shape drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder hitbox(Hitbox hitbox) {
            this.hitbox = hitbox;
            return this;
        }

        public Builder outlineColor(Color outlineColor) {
            this.outlineColor = outlineColor;
            return this;
        }

        public Builder fillColor(Color fillColor) {
            this.fillColor = fillColor;
            return this;
        }

        public Block build() {


            this.drawable.setFillColor(this.fillColor);
            this.drawable.setOutlineColor(this.outlineColor);
            this.drawable.setPosition(this.position.toVector());

            if (this.hitbox == null) {
                this.hitbox = new RectangleHitbox(this.position, this.height, this.width, this.drawable.getGlobalBounds());
            }

            return new Block(this.position, this.drawable, this.hitbox);
        }
    }
}
