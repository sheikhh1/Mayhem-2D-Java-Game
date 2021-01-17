package me.mayhem.game.entity.enemies.infected;

import me.mayhem.game.ai.Pathing;
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
     * @param type       - Type of Entity - eg Player/Enemy
     * @param position   - Current Position of entity relative to the game window
     * @param motion     - Motion of the entity eg if entity is moving
     * @param pathing    - Pathing for AI generated movement
     * @param attributes - Attributes an entity has - eg health
     */
    public Infected(EntityType type, Vector position, Vector motion, Pathing pathing, Attribute<?>... attributes) {
        super(type, position, motion, pathing, attributes);
    }

}
