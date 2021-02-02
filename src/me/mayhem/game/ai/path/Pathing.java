package me.mayhem.game.ai.path;

import me.mayhem.game.ai.path.impl.ForwardPathing;
import me.mayhem.game.ai.path.impl.JumpPathing;
import me.mayhem.game.ai.path.impl.NoPathing;
import me.mayhem.game.entity.Entity;

/**
 * Pathing Interface
 * Generates a vector dependent on the current vector of an entity
 */
public interface Pathing {

    Pathing NO_PATHING = new NoPathing();
    Pathing FORWARD_PATHING = new ForwardPathing();
    Pathing JUMP_PATHING = new JumpPathing();

    /**
     * updatePosition takes an entity and updates its position
     * @param entity - Eg - An enemy
     */
    void updatePosition(Entity entity);



}
