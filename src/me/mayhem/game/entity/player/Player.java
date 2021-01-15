package me.mayhem.game.entity.player;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.state.PlayerState;
import me.mayhem.util.Vector;

/**
 * Player Class
 */
public class Player extends Entity {

    private String name;
    private PlayerState[] states = new PlayerState[2];

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.ZERO, Pathing.NO_PATHING);

        this.name = name; // Name assigned and stored

        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(PlayerState.FALLING);
    }

    /**
     * Keyboard press listener sends a player state depending on which key has been pressed
     * @param state - Current state of the player
     */
   public void setState(PlayerState state) {
       if (state == null) {
           return;
       }

       PlayerState currentState = this.states[state.getIndex()];

       if (currentState == PlayerState.FALLING || currentState == PlayerState.JUMPING) {
           if (state == PlayerState.NO_MOTION) {
               this.setJumping(false);
               this.setFalling(false);
               this.getEntityPhysics().reset(state);
               this.states[state.getIndex()] = state;
           }
       } else if (currentState == PlayerState.NO_MOTION) {
           if (state == PlayerState.JUMPING) {
               this.setJumping(true);
               this.setFalling(false);
               this.states[state.getIndex()] = state;
           } else if (state == PlayerState.FALLING) {
               this.setFalling(true);
               this.setJumping(false);
               this.states[state.getIndex()] = state;
           }
       } else {
           this.states[state.getIndex()] = state;

           if (state == PlayerState.STANDING) {
               animate.setColumn(0);
               animate.setPause(true);
               this.setForward(false);
               this.setBack(false);
           } else if (state == PlayerState.BACK) {
               this.setForward(false);
               this.setBack(true);
           } else if (state == PlayerState.FORWARD) {
               this.setForward(true);
               this.setBack(false);
           }
       }
   }

   public PlayerState getState(int index) {
       return this.states[index];
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
