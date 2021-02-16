package me.mayhem.util.direction;

import me.mayhem.util.Vector;

import java.util.function.BiFunction;

public enum Direction {

    ABOVE((a, b) -> {
        Vector between = a.subtract(b).normalize();

        System.out.println(between.getY());

        return between.getY() < 0;
    }),
    BELOW((a, b) -> {
        Vector between = a.subtract(b).normalize();

        return between.getY() > 0;
    }),

    ;

    private final BiFunction<Vector, Vector, Boolean> checker;

    Direction(BiFunction<Vector, Vector, Boolean> checker) {
        this.checker = checker;
    }

    public boolean is(Vector a, Vector b) {
        return this.checker.apply(a.clone(), b);
    }
}
