package me.mayhem.game.entity.player;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.event.PlayerJumpEvent;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.event.EventManager;
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
        super(EntityType.PLAYER, position, Vector.getZero(), new SpriteHitbox(position, 80, 35), Pathing.NO_PATHING);

        this.animate.setSpritePosition(position.toVector());
        this.name = name;
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.FALLING);
    }

    public void tick() {
        super.tick();

        if (this.isForward()) {
            this.getEntityPhysics().moveForward();
            this.animate.setRow(11);
            this.animate.setPause(false);
        } else if (this.isBack()) {
            this.getEntityPhysics().moveBack();
            this.animate.setRow(9);
            this.animate.setPause(false);
        }

        if(this.isMelee() && this.getFacing().getX() == 1) {
            animate.setAvailableFrames(6);
            this.animate.setRow(15);
            this.animate.setPause(false);
        } else if (isMelee() && this.getFacing().getX() == -1) {
            animate.setAvailableFrames(6);
            this.animate.setRow(13);
            this.animate.setPause(false);
        }
        this.animate.setSpritePosition(this.getPosition().toVector());
    }

    @Override
    public void setJumping(boolean entityJump) {
        super.setJumping(entityJump);

        if (entityJump) {
            EventManager.callEvent(new PlayerJumpEvent(this));
        }
    }
}
