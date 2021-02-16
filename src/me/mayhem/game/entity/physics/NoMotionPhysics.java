package me.mayhem.game.entity.physics;

import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;

public class NoMotionPhysics extends EntityPhysics {

    public static final NoMotionPhysics NO_MOTION = new NoMotionPhysics();

    @Override
    public void setEntityMotion(Vector motion) {}

    @Override
    public void moveForward() {}

    @Override
    public void moveBack() {}

    @Override
    public void jump() {}

    @Override
    public void fall() {}

    @Override
    public void setJumpStrength(float jumpStrength) {}

    @Override
    public void setFallStrength(float fallStrength) {}

    @Override
    public void reset(EntityState state) {}

    @Override
    public float getFallStrength() {
        return 0;
    }
}
