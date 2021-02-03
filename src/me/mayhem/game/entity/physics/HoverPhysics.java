package me.mayhem.game.entity.physics;

import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;

public class HoverPhysics extends EntityPhysics {

    private static final float HOVER_STRENGTH = 1f;
    private Vector motion;

    public void setEntityMotion(Vector motion) {
        this.motion = motion;
    }


    @Override
    public void jump() {
        this.motion.subtract(0, HOVER_STRENGTH);
    }

    @Override
    public void fall() {
        this.motion.add(0, HOVER_STRENGTH);
    }

    @Override
    public void reset(EntityState entityState) {

    }
}