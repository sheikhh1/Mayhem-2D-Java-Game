package me.mayhem.util;

import org.jsfml.system.Vector2f;

import java.util.Objects;

/**
 *
 * Vector is a proxy class used to reduce dependency on JSFML
 * Also allows for mutability with the Vector class to reduce complications that come with Immutability (simplifies it
 * for the team)
 *
 */
public class Vector {

    private static final Vector ZERO = new Vector(Vector2f.ZERO);

    /**
     *
     * Gets a clone of the ZERO vector
     *
     * @return The zero vector
     */
    public static Vector getZero() {
        return ZERO.clone();
    }

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

    /**
     *
     * @return The X value of the Vector
     */
    public float getX() {
        return this.vector2f.x;
    }

    /**
     *
     * @return The Y value of the Vector
     */
    public float getY() {
        return this.vector2f.y;
    }

    /**
     *
     * Sets the vector to the X and Y values of the parameter
     * Allows for daisy chaining
     *
     * @param vector The vector to update the current values to
     * @return Itself
     */
    public Vector set(Vector vector) {
        return this.set(vector.getX(), vector.getY());
    }

    /**
     *
     * Sets the X and Y values to the two parameters
     *
     * @param x The new X value
     * @param y The new Y value
     * @return Itself
     */
    public Vector set(float x, float y) {
        this.vector2f = new Vector2f(x, y);
        return this;
    }

    /**
     *
     * Sets the X value to the specified parameter
     *
     * @param x The new X value
     * @return Itself
     */
    public Vector setX(float x) {
        this.vector2f = new Vector2f(x, this.getY());
        return this;
    }

    /**
     *
     * Sets the Y value to the specified parameter
     *
     * @param y The new Y value
     * @return Itself
     */
    public Vector setY(float y) {
        this.vector2f = new Vector2f(this.getX(), y);
        return this;
    }

    /**
     *
     * Adds the x and y values to the current {@link Vector}
     *
     * @param x the value to add to the X
     * @param y the value to add to the y
     * @return Itself
     */
    public Vector add(float x, float y) {
        this.vector2f = new Vector2f(this.getX() + x, this.getY() + y);
        return this;
    }

    /**
     *
     * Adds the x and y values from the parameter to the current {@link Vector}
     *
     * @param vector the values to add
     * @return Itself
     */
    public Vector add(Vector vector) {
        return this.add(vector.getX(), vector.getY());
    }

    /**
     *
     * Subtracts the x and y values to the current {@link Vector}
     *
     * @param x the value to take from the X
     * @param y the value to take from the y
     * @return Itself
     */
    public Vector subtract(float x, float y) {
        return this.add(-x, -y);
    }

    /**
     *
     * Subtracts the x and y values from the parameter to the current {@link Vector}
     *
     * @param vector the values to add
     * @return Itself
     */
    public Vector subtract(Vector vector) {
        return this.subtract(vector.getX(), vector.getY());
    }

    /**
     *
     * Multiplies the X and Y values by the parameter
     *
     * @param multiple The value to multiply by
     * @return Itself
     */
    public Vector multiply(float multiple) {
        this.vector2f = new Vector2f(this.getX() * multiple, this.getY() * multiple);
        return this;
    }

    /**
     *
     * Divides the X and Y values by the parameter
     *
     * @param multiple The value to divide by
     * @return Itself
     */
    public Vector divide(float multiple) {
        this.multiply(1 / multiple);
        return this;
    }

    /**
     *
     * Calculates the length of the vector
     *
     * @return the length of the vector
     */
    public double getLength() {
        return Math.sqrt(this.getLengthSquared());
    }

    /**
     *
     * Calculates the squares length of the vector
     *
     * @return The squared length of the vector
     */
    public double getLengthSquared() {
        return Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2);
    }

    /**
     *
     * Changes the length of the vector to 1
     *
     * @return Itself but now with {@link Vector#getLength()} == 1
     */
    public Vector normalize() {
        double length = this.getLength();

        if (length == 0) {
            return this.set(0, 0);
        }

        this.divide((float) length);
        return this;
    }

    /**
     *
     * Converts to JSFML internal vector
     * used for setting positions of things such as drawables
     *
     * @return {@link Vector2f} JSFML version
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Objects.equals(vector2f, vector.vector2f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector2f);
    }
}
