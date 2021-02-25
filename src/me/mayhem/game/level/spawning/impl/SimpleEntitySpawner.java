package me.mayhem.game.level.spawning.impl;

import me.mayhem.game.level.Level;
import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.game.level.spawning.SpawnPosition;

public class SimpleEntitySpawner implements EntitySpawner {

    @Override
    public void spawnEntities(Level level, LevelGenerator generator) {
        for (SpawnPosition position : generator.getEnemySpawnPositions()) {
            level.spawnEntity(position.getSpawnType().apply(position.getPosition(), level));
        }
    }
}
