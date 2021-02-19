package me.mayhem.game.level.spawning;

import me.mayhem.game.entity.Entity;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;

import java.util.function.BiFunction;

public class SpawnPosition {

    private final Vector position;
    private final BiFunction<Vector, Level, Entity> spawnType;

    public SpawnPosition(Vector position, BiFunction<Vector, Level, Entity> spawnType) {
        this.position = position;
        this.spawnType = spawnType;
    }

    public Vector getPosition() {
        return this.position;
    }

    public BiFunction<Vector, Level, Entity> getSpawnType() {
        return this.spawnType;
    }
}
