package me.mayhem.entity.drawable.drawablethings;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;

public class DrawableCircle {
    private CircleShape circle;

    public DrawableCircle(CircleShape circle) {
        this.circle = circle;
    }
    public void Draw(RenderWindow canvas){
        canvas.draw(circle);
    }
}
