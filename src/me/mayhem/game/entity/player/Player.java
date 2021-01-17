package me.mayhem.game.entity.player;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.util.Vector;

/**
 * Player Class
 */
public class Player extends Entity {

    private String name;

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.ZERO, Pathing.NO_PATHING);
        this.animate.setSpritePosition(position.toVector());
        this.name = name; // Name assigned and stored
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.FALLING);
    }

    public void tick() {
        if (this.isFalling()) {
            this.getEntityPhysics().fall();
        }

        if (this.isJumping()) {
            this.getEntityPhysics().jump();
        }

        if (this.isForward()) {
            this.getEntityPhysics().moveForward();
            this.animate.setRow(11);
            this.animate.setPause(false);
        } else if (this.isBack()) {
            this.getEntityPhysics().moveBack();
            this.animate.setRow(9);
            this.animate.setPause(false);
        }
        this.animate.setSpritePosition(this.getPosition().toVector());
    }
}
