package me.mayhem.game.level.difficulty;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.ai.impl.DifficultLevelGenerator;
import me.mayhem.game.level.layout.ai.impl.EasyLevelGenerator;
import me.mayhem.game.level.layout.ai.impl.MediumLevelGenerator;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.game.level.spawning.impl.DifficultEntitySpawner;
import me.mayhem.game.level.spawning.impl.EasyEntitySpawner;
import me.mayhem.game.level.spawning.impl.MediumEntitySpawner;

public enum Difficulty {

    EASY(new EasyLevelGenerator(), new EasyEntitySpawner()),
    MEDIUM(new MediumLevelGenerator(), new MediumEntitySpawner()),
    DIFFICULT(new DifficultLevelGenerator(), new DifficultEntitySpawner()),

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
