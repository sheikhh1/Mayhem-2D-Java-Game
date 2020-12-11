package me.mayhem.entity.sprites;

import me.mayhem.entity.Entity;
import me.mayhem.entity.EntityType;
import me.mayhem.entity.pathing.impl.NoPathing;
import me.mayhem.math.Vector;

/**
 * Player Class
 *
 */
public class Player extends Entity {

    private String name;

    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.ZERO, new NoPathing());

        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
