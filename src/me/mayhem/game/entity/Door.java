package me.mayhem.game.entity;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.util.Vector;

public class Door extends Entity {
    
    public Door(EntityType type, Vector position, Vector motion, Hitbox hitbox, Pathing pathing, Attribute<?>... attributes) {
        super(type, position, motion, hitbox, pathing, attributes);
    }
}
