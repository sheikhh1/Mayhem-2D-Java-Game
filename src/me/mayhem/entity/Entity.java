package me.mayhem.entity;

import me.mayhem.entity.hitbox.Hitbox;
import me.mayhem.entity.pathing.Pathing;
import me.mayhem.math.Vector;

public class Entity {

    private final EntityType type;

    private Vector position;
    private Vector motion;
    private Pathing pathing;
    private Hitbox hitbox;

    public Entity(EntityType type, Vector position, Vector motion, Pathing pathing) {
        this.type = type;
        this.position = position;
        this.motion = motion;
        this.pathing = pathing;
        this.hitbox = null;
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

    public Pathing getPathing() {return this.pathing;}

    public Hitbox getHitbox() {
        return this.hitbox;
    }
}
