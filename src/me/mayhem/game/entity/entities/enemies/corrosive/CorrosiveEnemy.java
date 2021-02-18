package me.mayhem.game.entity.entities.enemies.corrosive;

import me.mayhem.game.ai.path.impl.MoveBetweenBlocksPathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;

public class CorrosiveEnemy extends Entity {
    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public CorrosiveEnemy(Vector position, Level level) {
        super(EntityType.CORROSIVE, position, Vector.getZero(), new SpriteHitbox(position, 0,0 ), new MoveBetweenBlocksPathing());

        this.setState(EntityState.FALLING);
    }

    @Override
    public void tick() {
        super.tick();

        this.animate.setSpritePosition(this.getPosition().toVector());
    }
}
