package me.mayhem.util.screen;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.Entity;
import me.mayhem.util.Vector;

public class UtilScreen {

    public static final int SCREEN_RADIUS = 128;

    public static boolean isOffScreen(Entity entity) {
        return isOffScreenX(entity) || isOffScreenY(entity);
    }

    public static boolean isOffScreenX(Entity entity) {
        return isOffScreenLeftX(entity.getPosition(), entity.getMotion())
                || isOffScreenRightX(entity.getPosition(), entity.getMotion(), (int) entity.getWidth());
    }

    public static boolean isOffScreenRightX(Vector position, Vector motion, int width) {
        return (position.getX() + width + motion.getX()) > (Mayhem.SCREEN_WIDTH - SCREEN_RADIUS);
    }

    public static boolean isOffScreenLeftX(Vector position, Vector motion) {
        return (position.getX() + motion.getX()) < SCREEN_RADIUS;
    }

    public static boolean isOffScreenY(Entity entity) {
        return  isOffScreenTopY(entity.getPosition(), entity.getMotion()) || isOffScreenBottomY(entity.getPosition(), entity.getMotion(), (int) entity.getHeight());
    }

    public static boolean isOffScreenBottomY(Vector position, Vector motion, int height) {
        return (position.getY() + height + motion.getY()) > (Mayhem.SCREEN_HEIGHT - SCREEN_RADIUS);
    }

    public static boolean isOffScreenTopY(Vector position, Vector motion) {
        return (position.getY() + motion.getY()) < 0;
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
