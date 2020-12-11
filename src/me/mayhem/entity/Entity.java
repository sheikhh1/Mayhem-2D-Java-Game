package me.mayhem.entity;

import me.mayhem.attribute.Attribute;
import me.mayhem.entity.hitbox.Hitbox;
import me.mayhem.entity.pathing.Pathing;
import me.mayhem.math.Vector;

import java.util.Arrays;
import java.util.List;

public class Entity {

    private final EntityType type;

    private Vector position;
    private Vector motion;
    private Pathing pathing;
    private Hitbox hitbox;
    private List<Attribute<?>> attributes;

    public Entity(EntityType type, Vector position, Vector motion, Pathing pathing, Attribute<?>... attributes) {
        this.type = type;
        this.position = position;
        this.motion = motion;
        this.pathing = pathing;
        this.hitbox = null;
        this.attributes = Arrays.asList(attributes);
    }

    public EntityType getType() {
        return this.type;
    }

    public Vector getPosition() {
        return this.position;
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
