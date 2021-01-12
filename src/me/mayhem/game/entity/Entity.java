package me.mayhem.game.entity;

import me.mayhem.game.ai.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.RectangleHitbox;
import me.mayhem.util.Vector;

import java.util.Arrays;
import java.util.List;

/**
 * Entity Class
 * Used to store all the common information per entity in the game
 */
public class Entity {

    private final EntityType type;

    private Vector position;
    private Vector motion;
    private Pathing pathing;
    private Hitbox hitbox;
    private List<Attribute<?>> attributes;

    /**
     * Entity Constructor
     * @param type - Type of Entity - eg Player/Enemy
     * @param position - Current Position of entity relative to the game window
     * @param motion - Motion of the entity eg if entity is moving
     * @param pathing - Pathing for AI generated movement
     * @param attributes - Attributes an entity has - eg health
     */
    public Entity(EntityType type, Vector position, Vector motion, Pathing pathing, Attribute<?>... attributes) {
        this.type = type;
        this.position = position;
        this.motion = motion;
        this.pathing = pathing;
        this.hitbox = new RectangleHitbox(this.position, 20, 20);
        this.attributes = Arrays.asList(attributes);
    }

    public EntityType getType() {
        return this.type;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector getMotion() {
        return this.motion;
    }

    public Pathing getPathing() {
        return this.pathing;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public List<Attribute<?>> getAttributes() {
        return this.attributes;
    }

    public Attribute<?> getAttribute(String identifier) {
        for (Attribute<?> attribute : this.attributes) {
            if (attribute.getIdentifier().equals(identifier)) {
                return attribute;
            }
        }

        return null;
    }
}
