package me.mayhem.game.entity.physics;

import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;

public class HoverPhysics extends EntityPhysics {

    private static final float DEFAULT_JUMP_STRENGTH = 4f;
    private Vector motion;

    public void setEntityMotion(Vector motion) {
        this.motion = motion;
    }

    /**
     * Jump method for the Player
     */
    @Override
    public void jump() {
        this.motion.add(0, -1);
    }

    /**
     * Basic Fall Method Added
     */
    @Override
    public void fall() {
        this.motion.add(0, 1);
    }

    @Override
    public void reset(EntityState state) {

    }

}