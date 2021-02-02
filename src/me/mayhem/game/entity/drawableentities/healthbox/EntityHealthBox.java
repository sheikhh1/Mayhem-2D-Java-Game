package me.mayhem.game.entity.drawableentities.healthbox;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.drawable.drawablethings.DrawableSquare;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

import java.nio.file.Path;

public class EntityHealthBox {
    public static final int HEALTHBOXWIDTH = 100;
    public static final int HEALTHBOXHEIGHT = 20;

    private RectangleShape totalHealth;
    private RectangleShape currentHealth;


    public EntityHealthBox(Vector entityPosition){
        totalHealth = createHealthBox(new Vector(entityPosition.getX(), entityPosition.getY() - 30));
        currentHealth = createCurretHealth(new Vector(entityPosition.getX(), entityPosition.getY() - 30));

    }

    private RectangleShape createHealthBox(Vector position){
        RectangleShape rect = new RectangleShape(position.toVector());
        rect.setSize(new Vector(HEALTHBOXWIDTH,HEALTHBOXHEIGHT).toVector());
        rect.setFillColor(Color.RED);
        return rect;



    }
    private RectangleShape createCurretHealth(Vector position){
        RectangleShape rect = new RectangleShape(position.toVector());
        rect.setSize(new Vector(HEALTHBOXWIDTH,HEALTHBOXHEIGHT).toVector());
        rect.setFillColor(Color.GREEN);
        return rect;
    }

    public void Redraw(Entity entity){

        float newsize = calculateSize( (entity.getHealth()), entity.getType().getMaxHealth());

        currentHealth.setSize( new Vector(newsize, HEALTHBOXHEIGHT).toVector());


    }

    public void draw(RenderWindow window){

        window.draw(totalHealth);
        window.draw(currentHealth);
    }
    public int calculateSize(double currentHealth, double maxHealth){
        return (int) ((currentHealth/maxHealth) * 100);// returns the health as a percentage to be drawn
    }

}
