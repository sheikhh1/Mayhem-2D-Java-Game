package me.mayhem.math;

import org.jsfml.system.Vector2f;

public class Vector {

    public static final Vector ZERO = new Vector(Vector2f.ZERO);

    private Vector2f vector2f;

    public Vector(float x, float y) {
        this.vector2f = new Vector2f(x, y);
    }

    private Vector(Vector2f vector2f) {
        this.vector2f = vector2f;
    }

    public float getX() {
        return this.vector2f.x;
    }

    public float getY() {
        return this.vector2f.y;
    }

    public void add(float x, float y) {
        this.vector2f = new Vector2f(this.getX() + x, this.getY() + y);
    }

    public void subtract(float x, float y) {
        this.add(-x, -y);
    }

    public void multiply(float multiple) {
        this.vector2f = new Vector2f(this.getX() * multiple, this.getY() * multiple);
    }

    public void divide(float multiple) {
        this.multiply(1 / multiple);
    }

    public double getLength() {
        return Math.sqrt(this.getLengthSquared());
    }

    public double getLengthSquared() {
        return Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2);
    }

    public Vector2f toVector() {
        return this.vector2f;
    }
}
