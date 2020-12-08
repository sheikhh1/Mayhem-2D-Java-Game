package me.mayhem.entity.drawable.drawablethings;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

public class DrawableSquare {
    private RectangleShape rect;

    public DrawableSquare(RectangleShape rect) {
        this.rect = rect;
    }
    public void draw(RenderWindow canvas) {
        canvas.draw(this.rect);
    }
}
