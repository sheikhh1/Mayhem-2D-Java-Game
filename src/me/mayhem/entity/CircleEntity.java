package me.mayhem.entity;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class CircleEntity extends AbstractEntity<CircleShape> {

    public CircleEntity(int id, int priority, Vector2f position) {
        super(id, priority, new CircleShape());

        this.setPosition(position);
    }

    public float getRadius() {
        return this.getShape().getRadius();
    }

    public void setRadius(float radius) {
        this.getShape().setRadius(radius);
    }

    @Override
    public void draw(RenderWindow window) {
        window.draw(this.getShape());
    }
}
