package me.mayhem.util.direction;

import me.mayhem.util.Vector;

import java.util.function.BiFunction;

/**
 *
 * Enum for checking two vectors and what the direction is between them
 * For example, to check if A is below B it'd be Direction.BELOW.is(a, b);
 *
 * Clones the vectors to ensure that neither is modified when performing the checks
 */
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

    /**
     *
     * Default constructor taking a {@link BiFunction} that checks if the two vectors are in the given
     * direction
     *
     * @param checker The {@link BiFunction} used to check if the direction is correct
     */
    Direction(BiFunction<Vector, Vector, Boolean> checker) {
        this.checker = checker;
    }

    /**
     *
     * Determines if {@param a} is {@link Direction} {@param b} (i.e. if A is above B)
     *
     * @param a The first vector
     * @param b The second vector
     * @return true if correct - false if not
     */
    public boolean is(Vector a, Vector b) {
        return this.checker.apply(a.clone(), b);
    }
}
