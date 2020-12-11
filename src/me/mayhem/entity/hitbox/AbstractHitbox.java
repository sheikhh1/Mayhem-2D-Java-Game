package me.mayhem.entity.hitbox;

import me.mayhem.math.Vector;

public abstract class AbstractHitbox implements Hitbox {

    protected int priority;
    protected Vector position;
    protected Vector motion;
    protected int height;
    protected int width;

    public AbstractHitbox(int priority, Vector position, Vector motion, int height, int width) {
        this.priority = priority;
        this.position = position;
        this.motion = motion;
        this.height = height;
        this.width = width;
    }
}
