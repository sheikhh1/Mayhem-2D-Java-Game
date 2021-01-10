package me.mayhem.game.entity.drawable.drawablethings;

import me.mayhem.game.entity.drawable.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

/**
 *
 */
public class DrawableSquare implements Drawable {
    private RectangleShape rect;
    /**
     *
     * @param rect the rectangle to draw
     */
    public DrawableSquare(RectangleShape rect) {
        this.rect = rect;
    }
    /**
     *
     * @param canvas the canvas you a drawing onto
     */
    public void draw(RenderWindow canvas) {
        canvas.draw(this.rect);
    }
}
