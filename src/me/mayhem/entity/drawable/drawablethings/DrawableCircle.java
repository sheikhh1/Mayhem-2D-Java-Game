package me.mayhem.entity.drawable.drawablethings;

import me.mayhem.entity.drawable.Drawable;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;

/**
 * draws a circle on a canvas
 */
public class DrawableCircle implements Drawable {
    private CircleShape circle;
    /**
     *
     * @param circle the circle to draw
     */
    public DrawableCircle(CircleShape circle) {
        this.circle = circle;
    }

    /**
     *
     * @param canvas the canvas to draw onto
     */
    @Override
    public void draw(RenderWindow canvas) {
        canvas.draw(circle);
    }
}
