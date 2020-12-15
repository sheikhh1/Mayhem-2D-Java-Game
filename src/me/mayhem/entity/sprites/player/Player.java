package me.mayhem.entity.sprites.player;

import me.mayhem.entity.Entity;
import me.mayhem.entity.EntityType;
import me.mayhem.entity.pathing.impl.NoPathing;
import me.mayhem.math.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

import java.awt.*;

/**
 * Player Class
 */
public class Player extends Entity {

    private String name;
    private Vector position;

    //TODO: Implement DrawableSprite
    private RectangleShape test = new RectangleShape(new Vector(50,50).toVector());
    private float velX = 0f;
    private float velY = 0f;

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.ZERO, new NoPathing());

        this.name = name; // Name assigned and stored
        this.position = position;

        test.setFillColor(Color.BLUE);
        test.setScale(1,1);

    }

    public String getName() {
        return this.name;
    }

    /**
     * Velocity in X Direction
     * @param x
     */
    public void setVelX(float x) {
        this.velX = x;
        this.position.add(velX,0);
    }

    /**
     * Velocity in Y Direction
     * @param y
     */
    public void setVelY(float y){
        this.velY = y;
        this.position.add(0,velY);

    }

    /**
     * Updates position of the Player
     * @param rend
     */
    public void update(RenderWindow rend){
        test.setPosition(this.position.toVector());
        rend.draw(test);
    }


}
