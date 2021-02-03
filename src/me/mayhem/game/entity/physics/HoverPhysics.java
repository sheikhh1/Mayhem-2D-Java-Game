package me.mayhem.game.entity.physics;

public class HoverPhysics extends EntityPhysics {

    private static final float HOVER_STRENGTH = 1f;

    @Override
    public void jump() {
        super.motion.subtract(0, HOVER_STRENGTH);
    }

    @Override
    public void fall() {
        super.motion.add(0, HOVER_STRENGTH);
    }
}