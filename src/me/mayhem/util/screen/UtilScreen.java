package me.mayhem.util.screen;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.Entity;
import me.mayhem.util.Vector;

/**
 *
 * Static utility class for determining if an {@link Entity} or {@link Vector} are off the screen, and which side of the
 * screen they have run off (or are getting close to)
 *
 */
public class UtilScreen {

    /**
     *
     * The radius around the edge of the screen to prevent the player getting too close before moving the screen
     *
     */
    public static final int SCREEN_RADIUS = 256;

    /**
     *
     * Main method for checking if the entity is off/close to any edge of the screen (left, right, up, or down)
     *
     * @param entity The entity to check
     * @return If they're too close to the edge (or off it completely)
     */
    public static boolean isOffScreen(Entity entity) {
        return isOffScreenX(entity) || isOffScreenY(entity);
    }

    /**
     *
     * Checks if the given entity is off the left or right hand side of the screen using their positon, and motion
     * to pre-determine if they will be going off the screen
     *
     * @param entity The entity to check
     * @return True if off the screen - false if not
     */
    public static boolean isOffScreenX(Entity entity) {
        return isOffScreenLeftX(entity.getPosition(), entity.getMotion())
                || isOffScreenRightX(entity.getPosition(), entity.getMotion(), (int) entity.getWidth());
    }

    /**
     *
     * Checks if the position, with the given motion and width, is off the screen on the right (or within the radius)
     *
     * @param position The position to check
     * @param motion the motion to apply
     * @param width The width to apply
     * @return True if off the screen - false if not
     */
    public static boolean isOffScreenRightX(Vector position, Vector motion, int width) {
        return (position.getX() + width + motion.getX()) > (Mayhem.SCREEN_WIDTH - SCREEN_RADIUS);
    }


    /**
     *
     * Checks if the position, with the given motion, is off the screen on the left (or within the radius)
     * Width check is not required here as the entity's is always at the top left (JSFML)
     *
     * @param position The position to check
     * @param motion the motion to apply
     * @return True if off the screen - false if not
     */
    public static boolean isOffScreenLeftX(Vector position, Vector motion) {
        return (position.getX() + motion.getX()) < (SCREEN_RADIUS);
    }

    /**
     *
     * Checks if the given entity is off the top or bottom of the screen using their position, and motion
     * to pre-determine if they will be going off the screen
     *
     * @param entity The entity to check
     * @return True if off the screen - false if not
     */
    public static boolean isOffScreenY(Entity entity) {
        return isOffScreenTopY(entity.getPosition(), entity.getMotion()) || isOffScreenBottomY(entity.getPosition(), entity.getMotion(), (int) entity.getHeight());
    }

    /**
     *
     * Checks if the position, with given motion and height applied, is off the bottom of the screen (or within the radius)
     *
     * @param position The position to check
     * @param motion The motion to apply
     * @param height The height to apply
     * @return True if off the screen - false if not
     */
    public static boolean isOffScreenBottomY(Vector position, Vector motion, int height) {
        return (position.getY() + height + motion.getY()) > (Mayhem.SCREEN_HEIGHT - SCREEN_RADIUS);
    }

    /**
     *
     * Checks if the position, with given motion, is off the top of the screen (or within the radius)
     * Height is not required here as the entity's position always starts from the top left (JSFML)
     *
     * @param position The position to check
     * @param motion The motion to apply
     * @return True if off the screen - false if not
     */
    public static boolean isOffScreenTopY(Vector position, Vector motion) {
        return (position.getY() + motion.getY()) < SCREEN_RADIUS;
    }

    public static void fixEntityMotion(Entity entity) {
        if (isOffScreenLeftX(entity.getPosition(), entity.getMotion())) {
            float position = entity.getPosition().getX() + entity.getMotion().getX();
            entity.getMotion().add(position, 0);
        }

        if (isOffScreenRightX(entity.getPosition(), entity.getMotion(), (int) entity.getWidth())) {
            float position = (-1) * (entity.getPosition().getX() + entity.getWidth() + entity.getMotion().getX() - Mayhem.SCREEN_WIDTH);
            entity.getMotion().add(position, 0);
        }

        if (isOffScreenBottomY(entity.getPosition(), entity.getMotion(), (int) entity.getHeight())) {
            float position = (-1) * (entity.getPosition().getY() + entity.getHeight() + entity.getMotion().getY() - Mayhem.SCREEN_HEIGHT);
            entity.getMotion().add(0, position);
        }

        if (isOffScreenTopY(entity.getPosition(), entity.getMotion())) {
            float position = entity.getPosition().getY() + entity.getMotion().getY();
            entity.getMotion().add(0, position);
        }
    }
}
