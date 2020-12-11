package me.mayhem.entity.drawable.drawablethings;

import me.mayhem.entity.drawable.Drawable;
import org.jsfml.graphics.*;

/**
 * draws a sprite onto the canvas
 */
public class DrawableSprite implements Drawable {
    private Sprite mySprite;
    /**
     *
     * @param mySprite the sprite to be drawn
     */
    public DrawableSprite(Sprite mySprite) {
        this.mySprite = mySprite;
    }

    /**
     *
     * @param canvas the canvas to draw onto
     */
    @Override
    public void draw(RenderWindow canvas) {
        canvas.draw(this.mySprite);
    }
}
