package me.mayhem.entity.sprites.player;

import me.mayhem.entity.Entity;
import me.mayhem.entity.EntityType;
import me.mayhem.entity.pathing.impl.NoPathing;
import me.mayhem.entity.sprites.player.physics.PlayerPhysics;
import me.mayhem.math.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

/**
 * Player Class
 */
public class Player extends Entity {

    private String name;
    private Vector position;

    //TODO: Implement DrawableSprite
    private RectangleShape test = new RectangleShape(new Vector(50,50).toVector());
    public enum playerState {FORWARD,BACK,JUMPING,FALLING,STANDING};
    PlayerPhysics p = new PlayerPhysics();

    // Determine what the player is currently doing
    public boolean jumping;
    public boolean forward;
    public  boolean back;

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.ZERO, new NoPathing());

        this.name = name; // Name assigned and stored
        this.position = position;

        p.setPlayerPosition(this.position);

        test.setFillColor(Color.BLUE);
        test.setScale(1,1);

    }

    public String getName() {
        return this.name;
    }

    /**
     * Keyboard press listener sends a player state depending on which key has been pressed
     * @param state - Current state of the player
     */
   public void setState(playerState state){
        if (state == playerState.JUMPING){
            jumping = true;
        } else if (state == playerState.FORWARD){
            forward = true;
            back = false;
        } else if (state == playerState.BACK){
            back = true;
            forward = false;
        } else if (state == playerState.STANDING){
            forward = false;
            back = false;
        }
   }

    /**
     * Updates position of the Player depending on user input
     * Outputs onto main window
     * @param rend
     */
    public void update(RenderWindow rend){
        if (jumping){
            p.jump();
            if (p.checkCollision()){jumping = false;}
        }
        if (forward){
            p.moveForward();
        } else if (back){
            p.moveBack();
        }
        test.setPosition(this.position.toVector());
        rend.draw(test);
    }


}
