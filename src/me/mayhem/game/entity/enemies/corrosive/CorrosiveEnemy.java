package me.mayhem.game.entity.enemies.corrosive;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.enemies.infected.state.InfectedState;
import me.mayhem.util.Vector;

public class CorrosiveEnemy extends Entity {
    private InfectedState[] states = new InfectedState[2];
    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public CorrosiveEnemy(Vector position) {
        super(EntityType.INFECTED, position, Vector.getZero(), new SpriteHitbox(position, 0,0 ), Pathing.FORWARD_PATHING);

    }

    public InfectedState getInfectedState(int index){
        return this.states[index];
    }

    @Override
    public void tick() {

    }
}
