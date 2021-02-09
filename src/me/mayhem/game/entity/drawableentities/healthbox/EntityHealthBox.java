package me.mayhem.game.entity.drawableentities.healthbox;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.event.EntityDamageByEntityEvent;
import me.mayhem.game.event.struct.EventListener;
import me.mayhem.game.event.struct.EventPriority;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

import java.util.Objects;

public class EntityHealthBox {

    private static final int HEALTH_BOX_WIDTH = 50;
    private static final int HEALTH_BOX_HEIGHT = 10;

    private final Entity parent;
    private final RectangleShape totalHealth;
    private final RectangleShape currentHealth;

    public EntityHealthBox(Entity parent, Vector entityPosition) {
        this.parent = parent;
        this.totalHealth = this.createHealthBox(new Vector(entityPosition.getX() - 25, entityPosition.getY() - 30), Color.RED);
        this.currentHealth = this.createHealthBox(new Vector(entityPosition.getX() - 25, entityPosition.getY() - 30), Color.GREEN);
    }

    private RectangleShape createHealthBox(Vector position, Color fillColour) {
        RectangleShape rectangleShape = new RectangleShape(position.toVector());

        rectangleShape.setPosition(position.toVector());
        rectangleShape.setSize(new Vector(HEALTH_BOX_WIDTH, HEALTH_BOX_HEIGHT).toVector());
        rectangleShape.setFillColor(fillColour);

        return rectangleShape;
    }

    @EventListener(priority = EventPriority.HIGHEST)
    public void onEntityDamaged(EntityDamageByEntityEvent event) {
        if (Objects.equals(this.parent, event.getAttacked())) {
            this.redrawHealthBars(event.getAttacker());
        } else if (Objects.equals(this.parent, event.getAttacker())) {
            this.redrawHealthBars(event.getAttacker());
        }
    }

    public void redrawHealthBars(Entity entity) {
        float newSize = this.calculateSize((entity.getHealth()), entity.getType().getMaxHealth());

        this.currentHealth.setSize(new Vector(newSize, HEALTH_BOX_HEIGHT).toVector());
    }

    public void updatePosition(Entity entity) {
        this.totalHealth.setPosition(entity.getCenter().add(-this.totalHealth.getGlobalBounds().width / 2.3f, -40).toVector());
        this.currentHealth.setPosition(entity.getCenter().add(-this.totalHealth.getGlobalBounds().width / 2.3f, -40).toVector());
    }

    public void draw(RenderWindow window, Entity entity) {
        this.updatePosition(entity);

        window.draw(this.totalHealth);
        window.draw(this.currentHealth);
    }

    /**
     *
     * Calculates the percentage health of the max health
     *
     * @param currentHealth the current health of the {@link Entity}
     * @param maxHealth the maximum health of the {@link Entity}
     * @return the percentage health
     */
    public int calculateSize(double currentHealth, double maxHealth) {
        return (int) ((currentHealth / maxHealth) * 50);
    }
}
