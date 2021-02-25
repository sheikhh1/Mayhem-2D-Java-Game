package me.mayhem.game.level.difficulty;

import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.game.level.spawning.impl.SimpleEntitySpawner;

public enum Difficulty {

    EASY(new SimpleEntitySpawner()),
    MEDIUM(new SimpleEntitySpawner()),
    DIFFICULT(new SimpleEntitySpawner()),

    ;

    private final EntitySpawner spawner;

    Difficulty(EntitySpawner spawner) {
        this.spawner = spawner;
    }

    public EntitySpawner getSpawner() {
        return this.spawner;
    }
}
