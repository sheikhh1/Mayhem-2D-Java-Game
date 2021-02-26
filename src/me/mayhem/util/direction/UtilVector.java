package me.mayhem.util.direction;

import me.mayhem.game.entity.Entity;
import me.mayhem.util.Vector;

/**
 *
 * A static utility method for handling getting the distance between two vectors and two entities without
 * modifying the existing positions of either vector or entity (i.e. reduces duplicate code)
 *
 */
public class UtilVector {

    /**
     *
     * Obtains the distance squared between the two vectors (in pixels squared)
     * Clones the first position as to prevent modifying the position of the first vector when doing the calculations
     *
     * @param first The first position
     * @param second The second position
     * @return The distance squared in pixels squared
     */
    public static double getDistanceSquared(Vector first, Vector second) {
        return first.clone().subtract(second).getLengthSquared();
    }

    /**
     *
     * A method to determine if the second entity is in the sight of the first entity (i.e. if the first entity is
     * facing the second entity)
     * Clones the entity's positions to prevent moving the entities whilst performing calculations
     *
     * @param entity The first entity
     * @param other The second entity to check if is in sight
     * @return True if the first entity faces the second - false if not
     */
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
