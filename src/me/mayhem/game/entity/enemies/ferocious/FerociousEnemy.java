package me.mayhem.game.entity.enemies.ferocious;

import me.mayhem.game.ai.path.impl.MoveToPlayerPathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.enemies.Enemy;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;


public class FerociousEnemy extends Entity implements Enemy {
    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public FerociousEnemy(Vector position, Level level) {
        super(EntityType.FEROCIOUS, position, Vector.getZero(), new SpriteHitbox(position, 0, 80), new MoveToPlayerPathing(level));
        this.animate.setSpritePosition(position.toVector());
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.FALLING);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isBack()) {
            this.animate.setRow(9);
            this.animate.setPause(false);
        } else if (this.isForward()) {
            this.animate.setRow(11);
            this.animate.setPause(false);
        }
    }

    @Override
    public void attack(Player player) {
        if (this.getFacing().normalize().equals(player.getFacing().normalize())) {
            player.getMotion().add(player.getFacing().clone().add(0, -1).multiply(4));
            this.getMotion().add(this.getFacing().clone().multiply(-1).multiply(5));
        } else {
            player.getMotion().add(this.getFacing().clone().add(0, -1).multiply(4));
            this.getMotion().add(player.getFacing().clone().multiply(5));
        }
    }
}
