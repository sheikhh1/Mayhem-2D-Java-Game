package me.mayhem.game.entity.enemies.infected;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.ai.impl.ForwardPathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.enemies.infected.state.InfectedState;
import me.mayhem.util.Vector;


public class Infected extends Entity {
    private InfectedState[] states = new InfectedState[2];
    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public Infected(Vector position) {
        super(EntityType.INFECTED, position, Vector.ZERO, Pathing.FORWARD_PATHING);

    }

    public InfectedState getState(int index){
        return this.states[index];
    }

}
