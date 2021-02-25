package me.mayhem.game.level.spawning;

import me.mayhem.util.Vector;

public class SpecialPosition {

    private final String id;
    private final Vector position;

    public SpecialPosition(String id, Vector position) {
        this.id = id;
        this.position = position;
    }

    public String getId() {
        return this.id;
    }

    public Vector getPosition() {
        return this.position;
    }
}
