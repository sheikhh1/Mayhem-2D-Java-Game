package me.mayhem.game.level.difficulty;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.ai.impl.ImageLevelGenerator;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.game.level.spawning.impl.SimpleEntitySpawner;

public enum Difficulty {

    EASY(new ImageLevelGenerator(), new SimpleEntitySpawner()),
    MEDIUM(new ImageLevelGenerator(), new SimpleEntitySpawner()),
    DIFFICULT(new ImageLevelGenerator(), new SimpleEntitySpawner()),

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
