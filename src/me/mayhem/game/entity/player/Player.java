package me.mayhem.game.entity.player;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.animation.PlayerAnimation;
import me.mayhem.game.entity.player.physics.PlayerPhysics;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;

/**
 * Player Class
 */
public class Player extends Entity {

    private String name;
    private PlayerPhysics playerPhysics = new PlayerPhysics();
    private PlayerAnimation animate = new PlayerAnimation();

    // Determine what the player is currently doing
    private boolean jumping = false;
    private boolean forward = false;
    private boolean back = false;
    private boolean fall = true;

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.ZERO, Pathing.NO_PATHING);

        this.name = name; // Name assigned and stored

        playerPhysics.setPlayerMotion(this.getMotion());
    }

    public String getName() {
        return this.name;
    }

    public boolean isJumping() {
        return this.jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isForward() {
        return this.forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean isBack() {
        return this.back;
    }

    public void setBack(boolean back) {
        this.back = back;
    }

    public boolean isFall() {
        return this.fall;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }

    public PlayerPhysics getPlayerPhysics() {
        return this.playerPhysics;
    }

    /**
     * Keyboard press listener sends a player state depending on which key has been pressed
     * @param state - Current state of the player
     */
   public void setState(PlayerState state){
        if (state == PlayerState.JUMPING){
            this.setJumping(true);
        } else if (state == PlayerState.FORWARD){
            this.forward = true;
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
            this.playerPhysics.fall();

//            if (this.playerPhysics.checkCollision()) {
//                this.setFall(false);
//            }
        }

        if (this.isJumping()) {
            this.playerPhysics.jump();

//            if (this.playerPhysics.checkCollision()) {
//                this.setJumping(false);
//            }
        }

        if (this.isForward()) {
            this.playerPhysics.moveForward();
            animate.setRow(11);
            animate.setPause(false);
        } else if (this.isBack()) {
            this.playerPhysics.moveBack();
            animate.setRow(9);
            animate.setPause(false);
        }
        animate.setSpritePosition(this.getPosition().toVector());
    }
}
