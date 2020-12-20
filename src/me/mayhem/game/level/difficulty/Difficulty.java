package me.mayhem.game.level.difficulty;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.spawning.EntitySpawner;

public enum Difficulty {

    EASY(null, null),
    MEDIUM(null, null),
    HARD(null, null),

    ;

    private final LevelGenerator generator;
    private final EntitySpawner spawner;

    Difficulty(LevelGenerator generator, EntitySpawner spawner) {
        this.generator = generator;
        this.spawner = spawner;
    }

    public LevelGenerator getGenerator() {
        return this.generator;
    }

    public EntitySpawner getSpawner() {
        return this.spawner;
    }
}
