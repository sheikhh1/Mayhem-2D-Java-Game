package me.mayhem.hitbox;

import me.mayhem.math.Vector;

public abstract class AbstractHitbox implements Hitbox {

    protected int priority;
    protected Vector position;
    protected int height;
    protected int width;

    /**
     *
     * @param priority the priority of the hixbox
     * @param position the position of the hitbox
     * @param height the height of the hitbox
     * @param width the width of the hitbox
     */
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
