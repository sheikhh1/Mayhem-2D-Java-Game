package me.mayhem.game.entity.physics.type;

import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.util.Vector;

public class HoverPhysics extends EntityPhysics {

    private static final float HOVER_STRENGTH = 1f;

    public HoverPhysics(EntityType entityType, Vector motion) {
        super(entityType, motion);
    }

    @Override
    public void jump() {
        super.motion.subtract(0, HOVER_STRENGTH);
    }

    @Override
    public void fall() {
        super.motion.add(0, HOVER_STRENGTH);
    }
}