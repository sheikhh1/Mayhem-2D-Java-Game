package me.mayhem.game.level.spawning;

import me.mayhem.util.Vector;

public class SpawnPosition {

    private final Vector position;
    private final Runnable spawnType;

    public SpawnPosition(Vector position, Runnable spawnType) {
        this.position = position;
        this.spawnType = spawnType;
    }

    public Vector getPosition() {
        return this.position;
    }

    public Runnable getSpawnType() {
        return this.spawnType;
    }
}
