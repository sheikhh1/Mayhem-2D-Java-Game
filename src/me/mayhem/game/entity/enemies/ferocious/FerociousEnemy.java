package me.mayhem.game.entity.enemies.ferocious;

import me.mayhem.game.ai.path.impl.MoveToPlayerPathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;


public class FerociousEnemy extends Entity {
    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public FerociousEnemy(Vector position, Level level) {
        super(EntityType.INFECTED, position, Vector.getZero(), new SpriteHitbox(position, 0, 80), new MoveToPlayerPathing(level));
        this.animate.setSpritePosition(position.toVector());
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.FALLING);
    }

    @Override
    public void tick() {
        if (this.isFalling()) {
            this.getEntityPhysics().fall();
        }

        if (this.isJumping()) {
            this.getEntityPhysics().jump();
        }

        if (this.isBack()) {
            this.animate.setRow(9);
            this.animate.setPause(false);
        } else if (this.isForward()) {
            this.animate.setRow(11);
            this.animate.setPause(false);
        }
    }
}
