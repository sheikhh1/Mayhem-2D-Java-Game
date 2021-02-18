package me.mayhem.util.direction;

import me.mayhem.util.Vector;

public class UtilVector {

    public static double getDistanceSquared(Vector first, Vector second) {
        return first.clone().subtract(second).getLengthSquared();
    }
}
