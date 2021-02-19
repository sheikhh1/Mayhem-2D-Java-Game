package me.mayhem.util.direction;

import me.mayhem.util.Vector;

import java.util.function.BiFunction;

public enum Direction {

    ABOVE((a, b) -> {
        Vector between = a.subtract(b).normalize();

        return between.getY() < 0;
    }),
    BELOW((a, b) -> {
        Vector between = a.subtract(b).normalize();

        return between.getY() > 0;
    }),
    LEFT((a, b) -> {
        Vector between = a.subtract(b).normalize();

        return between.getX() > 0;
    }),
    RIGHT((a, b) -> {
        Vector between = a.subtract(b).normalize();

        return between.getX() < 0;
    })

    ;

    private final BiFunction<Vector, Vector, Boolean> checker;

    Direction(BiFunction<Vector, Vector, Boolean> checker) {
        this.checker = checker;
    }

    /**
     *
     * Determines if {@param a} is {@link Direction} {@param b} (i.e. if A is above B)
     *
     * @param a
     * @param b
     * @return
     */
    public boolean is(Vector a, Vector b) {
        return this.checker.apply(a.clone(), b);
    }
}
