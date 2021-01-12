package me.mayhem.game.entity.player;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.animation.PlayerAnimation;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;

/**
 * Player Class
 */
public class Player extends Entity {

    private String name;
    private PlayerAnimation animate = new PlayerAnimation();

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.ZERO, Pathing.NO_PATHING);

        this.name = name; // Name assigned and stored

        this.getEntityPhysics().setEntityMotion(this.getMotion());
    }

    /**
     * Keyboard press listener sends a player state depending on which key has been pressed
     * @param state - Current state of the player
     */
   public void setState(PlayerState state){
        if (state == PlayerState.JUMPING){
            this.setJumping(true);
        } else if (state == PlayerState.FORWARD){
            this.setForward(true);
            this.setBack(false);
        } else if (state == PlayerState.BACK){
            this.setForward(false);
            this.setBack(true);
        } else if (state == PlayerState.STANDING){
            animate.setColumn(0);
            animate.setPause(true);
            this.setForward(false);
            this.setBack(false);
        }
   }

    /**
     * Updates position of the Player depending on user input
     * Outputs onto main window
     * @param window
     */
    public void update(RenderWindow window) {
        animate.playAnimation(window);
    }

    public void tick() {
        if (this.isFall()) {
            this.getEntityPhysics().fall();
        }

        if (this.isJumping()) {
            this.getEntityPhysics().jump();

        }

        if (this.isForward()) {
            this.getEntityPhysics().moveForward();
            animate.setRow(11);
            animate.setPause(false);
        } else if (this.isBack()) {
            this.getEntityPhysics().moveBack();
            animate.setRow(9);
            animate.setPause(false);
        }
        animate.setSpritePosition(this.getPosition().toVector());
    }
}
