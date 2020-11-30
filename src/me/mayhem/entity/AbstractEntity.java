package me.mayhem.entity;

import org.jsfml.system.Vector2f;

public abstract class AbstractEntity implements Entity {

    private final int id;

    private Vector2f position;

    public AbstractEntity(int id) {
        this(id, new Vector2f(0, 0));
    }

    public AbstractEntity(int id, Vector2f position) {
        this.id = id;
        this.position = position;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Vector2f getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
    }
}
