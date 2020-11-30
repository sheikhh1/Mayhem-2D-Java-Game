package me.mayhem.entity;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class CircleEntity extends AbstractEntity {

    private int radius;

    public CircleEntity(int id) {
        super(id);

        this.radius = 0;
    }

    public CircleEntity(int id, int priority, Vector2f position) {
        super(id, priority, position);

        this.radius = 0;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(RenderWindow window) {
        CircleShape circleShape = new CircleShape(this.radius);

        circleShape.setOrigin(this.getPosition());

        window.draw(circleShape);
    }
}
