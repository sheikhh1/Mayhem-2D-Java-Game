package me.mayhem.entity.drawable.drawablethings;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;

/**
 * draws a circle on a canvas
 */
public class DrawableCircle {
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
    public void Draw(RenderWindow canvas){
        canvas.draw(circle);
    }
}
