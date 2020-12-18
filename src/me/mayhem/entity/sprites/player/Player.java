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

    //TODO: Implement DrawableSprite
    private RectangleShape test = new RectangleShape(new Vector(50,50).toVector());
    private PlayerPhysics playerPhysics = new PlayerPhysics();

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
        super(EntityType.PLAYER, position, Vector.ZERO, new NoPathing());

        this.name = name; // Name assigned and stored

        this.test.setFillColor(Color.BLUE);
        this.test.setScale(1,1);

    }

    public String getName() {
        return this.name;
    }

    /**
     * Keyboard press listener sends a player state depending on which key has been pressed
     * @param state - Current state of the player
     */
   public void setState(PlayerState state){
        if (state == PlayerState.JUMPING){
            this.jumping = true;
        } else if (state == PlayerState.FORWARD){
            this.forward = true;
            this.back = false;
        } else if (state == PlayerState.BACK){
            this.back = true;
            this.forward = false;
        } else if (state == PlayerState.STANDING){
            this.forward = false;
            this.back = false;
        }
   }

    /**
     * Updates position of the Player depending on user input
     * Outputs onto main window
     * @param rend
     */
    public void update(RenderWindow rend) {
        if (this.fall) {
            this.playerPhysics.fall();

            if (this.playerPhysics.checkCollision()) {
                fall = false;
            }
        }

        if (this.jumping) {
            this.playerPhysics.jump();
            if (this.playerPhysics.checkCollision()) {
                this.jumping = false;
            }
        }

        if (this.forward) {
            this.playerPhysics.moveForward();
        } else if (back) {
            this.playerPhysics.moveBack();
        }

        this.test.setPosition(this.getPosition().toVector());
        rend.draw(this.test);
    }
}
