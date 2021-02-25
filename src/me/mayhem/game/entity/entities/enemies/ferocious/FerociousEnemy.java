package me.mayhem.game.entity.entities.enemies.ferocious;

import me.mayhem.game.ai.path.impl.MoveBetweenBlocksPathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.entities.enemies.Enemy;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import org.jsfml.system.Clock;


public class FerociousEnemy extends Entity implements Enemy {

    private final Clock attackedAnimateClock = new Clock();

    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public FerociousEnemy(Vector position, Level level) {
        super(EntityType.FEROCIOUS, position, Vector.getZero(), new SpriteHitbox(position,55,40), new MoveBetweenBlocksPathing());

        this.animate.setSpritePosition(position.toVector());
    }

    @Override
    public void attack(Player player) {
        if (!player.isDead()) {
            player.damage(this, 1);
            this.setState(EntityState.MELEE);
            this.attackedAnimateClock.restart();
        }
    }
}
