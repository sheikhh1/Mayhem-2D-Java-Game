package me.mayhem.game.entity.drawableentities.healthbox;

import me.mayhem.game.entity.Entity;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

public class EntityHealthBox {
    public static final int HEALTHBOXWIDTH = 50;
    public static final int HEALTHBOXHEIGHT = 10;

    private RectangleShape totalHealth;
    private RectangleShape currentHealth;


    public EntityHealthBox(Vector entityPosition){

        totalHealth = createHealthBox(new Vector(entityPosition.getX() - 25, entityPosition.getY() - 30));
        currentHealth = createCurretHealth(new Vector(entityPosition.getX()- 25, entityPosition.getY() - 30));

    }

    private RectangleShape createHealthBox(Vector position){
        RectangleShape rect = new RectangleShape(position.toVector());

        rect.setPosition(position.toVector());
        rect.setSize(new Vector(HEALTHBOXWIDTH,HEALTHBOXHEIGHT).toVector());
        rect.setFillColor(Color.RED);

        return rect;
    }
    private RectangleShape createCurretHealth(Vector position){
        RectangleShape rect = new RectangleShape();

        rect.setPosition(position.toVector());
        rect.setSize(new Vector(HEALTHBOXWIDTH,HEALTHBOXHEIGHT).toVector());
        rect.setFillColor(Color.GREEN);

        return rect;
    }

    public void Redraw(Entity entity){

        float newsize = calculateSize( (entity.getHealth()), entity.getType().getMaxHealth());

        currentHealth.setSize( new Vector(newsize, HEALTHBOXHEIGHT).toVector());
    }
    public void rePosition(Entity entity){
        totalHealth.setPosition(entity.getCenter().add(-totalHealth.getGlobalBounds().width / 2.3f, -40).toVector());
        currentHealth.setPosition(entity.getCenter().add(-totalHealth.getGlobalBounds().width / 2.3f, -40).toVector());
    }

    public void draw(RenderWindow window, Entity entity){
        rePosition(entity);
        window.draw(totalHealth);
        window.draw(currentHealth);
    }

    public int calculateSize(double currentHealth, double maxHealth){
        return (int) ((currentHealth/maxHealth) * 100);// returns the health as a percentage to be drawn
    }

}
