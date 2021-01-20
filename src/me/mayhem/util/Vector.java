package me.mayhem.util;

import org.jsfml.system.Vector2f;

/**
 *
 * Vector is a proxy class used to reduce dependency on JSFML
 *
 */
public class Vector {

    public static final Vector ZERO = new Vector(Vector2f.ZERO);

    private Vector2f vector2f;

    /**
     *
     * Creates a new vector with given X and Y values
     *
     * @param x Any real number
     * @param y Any real number
     */
    public Vector(float x, float y) {
        this.vector2f = new Vector2f(x, y);
    }

    /**
     *
     * A private constructor for constants
     *
     * @param vector2f The constant vector
     */
    private Vector(Vector2f vector2f) {
        this.vector2f = vector2f;
    }

    public float getX() {
        return this.vector2f.x;
    }

    public float getY() {
        return this.vector2f.y;
    }

    public void set(Vector vector) {
        this.set(vector.getX(), vector.getY());
    }

    public void set(float x, float y) {
        this.vector2f = new Vector2f(x, y);
    }

    public void setX(float x) {
        this.vector2f = new Vector2f(x, this.getY());
    }

    public void setY(float y) {
        this.vector2f = new Vector2f(this.getX(), y);
    }

    public Vector add(float x, float y) {
        this.vector2f = new Vector2f(this.getX() + x, this.getY() + y);
        return this;
    }

    public Vector add(Vector vector) {
        return this.add(vector.getX(), vector.getY());
    }

    public Vector subtract(float x, float y) {
        return this.add(-x, -y);
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

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + this.getX() +
                ", y=" + this.getY() +
                '}';
    }

    @Override
    public Vector clone() {
        return new Vector(this.getX(), this.getY());
    }
}
