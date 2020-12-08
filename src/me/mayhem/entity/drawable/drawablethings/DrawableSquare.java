package me.mayhem.entity.drawable.drawablethings;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

/**
 *
 */
public class DrawableSquare {
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
