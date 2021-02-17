package me.mayhem.game.entity.entities.enemies.infected;

import me.mayhem.game.ai.path.impl.MoveToPlayerPathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.entities.enemies.Enemy;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import org.jsfml.system.Clock;


public class InfectedEnemy extends Entity implements Enemy {

    private final Clock attackedAnimateClock = new Clock();

    /**
     * Entity Constructor
     *
     * @param position   - Current Position of entity relative to the game window
     */
    public InfectedEnemy(Vector position, Level level) {
        super(EntityType.INFECTED, position, Vector.getZero(), new SpriteHitbox(position,55,40), new MoveToPlayerPathing(level));

        this.animate.setSpritePosition(position.toVector());
        this.setState(EntityState.FALLING);
    }

    public void tick() {
        super.tick();

        if (this.isBack()) {
            this.animate.setRow(9);
            this.animate.setPause(false);
        } else if (this.isForward()){
            this.animate.setRow(11);
            this.animate.setPause(false);
        }

        if(this.isMelee() && this.getFacing().getX() == 1) {
            this.animate.setAvailableFrames(6);
            this.animate.setRow(15);
            this.animate.setPause(false);
        } else if (isMelee() && this.getFacing().getX() == -1) {
            this.animate.setAvailableFrames(6);
            this.animate.setRow(13);
            this.animate.setPause(false);
        }

        if (this.attackedAnimateClock.getElapsedTime().asMilliseconds() > 100) {
            this.setMelee(false);
        }

        this.animate.setSpritePosition(this.getPosition().toVector());
    }

    @Override
    public void attack(Player player) {
        player.damage(this, 1);
        this.setState(EntityState.MELEE);
        this.attackedAnimateClock.restart();
    }
}
