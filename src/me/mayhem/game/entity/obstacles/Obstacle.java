package me.mayhem.game.entity.obstacles;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;

public class Obstacle extends Entity {

    public Obstacle(Vector position, Level level) {
        super(EntityType.SPIKES, position, Vector.getZero(), new SpriteHitbox(position, 48,48 ), Pathing.NO_PATHING);
        this.setState(EntityState.NO_MOTION);
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.animate.setSpritePosition(position.toVector());
    }

    public void tick() {
        this.animate.setSpritePosition(this.getPosition().toVector());
    }
}
