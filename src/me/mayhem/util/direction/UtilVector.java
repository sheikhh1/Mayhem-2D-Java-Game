package me.mayhem.util.direction;

import me.mayhem.game.entity.Entity;
import me.mayhem.util.Vector;

public class UtilVector {

    public static double getDistanceSquared(Vector first, Vector second) {
        return first.clone().subtract(second).getLengthSquared();
    }

    public static boolean inSight(Entity entity, Entity other) {
        Vector betweenEntities = entity.getPosition().clone().subtract(other.getPosition()).normalize();

        if (betweenEntities.getX() < 0 && entity.getFacing().getX() > 0) {
            return true;
        }

        if (betweenEntities.getX() > 0 && entity.getFacing().getX() < 0) {
            return true;
        }

        return false;
    }
}
