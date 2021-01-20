package me.mayhem.game.entity.enemies.infected;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;


public class InfectedEnemy extends Entity {
    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public InfectedEnemy(Vector position) {
        super(EntityType.INFECTED, position, Vector.getZero(), new SpriteHitbox(position,80,45), Pathing.NO_PATHING);

        this.animate.setSpritePosition(position.toVector());
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.FALLING);
    }

    public void tick() {
        if (this.isFalling()) {
            this.getEntityPhysics().fall();
        }
        this.animate.setSpritePosition(this.getPosition().toVector());
    }

}
