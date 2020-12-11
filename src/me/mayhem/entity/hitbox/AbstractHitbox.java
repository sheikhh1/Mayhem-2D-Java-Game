package me.mayhem.entity.hitbox;

import me.mayhem.math.Vector;

public abstract class AbstractHitbox implements Hitbox {

    protected int priority;
    protected Vector position;
    protected int height;
    protected int width;

    public AbstractHitbox(int priority, Vector position, int height, int width) {
        this.priority = priority;
        this.position = position;
        this.height = height;
        this.width = width;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public Vector asVector() {
        return this.position;
    }
}
