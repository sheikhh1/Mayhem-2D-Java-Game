package me.mayhem.entity;

import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public abstract class AbstractEntity<T extends Shape> implements Entity {

    private final int id;
    private final int priority;
    private final T shape;

    public AbstractEntity(int id, int priority, T shape) {
        this.id = id;
        this.priority = priority;
        this.shape = shape;
    }

    protected T getShape() {
        return this.shape;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public Vector2f getPosition() {
        return this.shape.getPosition();
    }

    @Override
    public void setPosition(Vector2f position) {
        this.shape.setPosition(position);
    }


}
