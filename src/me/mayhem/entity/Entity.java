package me.mayhem.entity;

import me.mayhem.entity.pathing.Pathing;
import me.mayhem.math.Vector;

public class Entity {

    private Vector position;
    private Vector motion;
    private Pathing pathing;

    public Vector getPosition() {
        return this.position;
    }

    public Vector getMotion() {
        return this.motion;
    }

    public Pathing getPathing() {return this.pathing;}

}
