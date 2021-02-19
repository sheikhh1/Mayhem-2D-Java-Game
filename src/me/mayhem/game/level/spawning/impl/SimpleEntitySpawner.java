package me.mayhem.game.level.spawning.impl;

import me.mayhem.game.level.Level;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.game.level.spawning.SpawnPosition;

public class SimpleEntitySpawner implements EntitySpawner {

    @Override
    public void spawnEntities(Level level) {
        for (SpawnPosition position : level.getDifficulty().getGenerator().getEnemySpawnPositions()) {
            level.spawnEntity(position.getSpawnType().apply(position.getPosition(), level));
        }
    }
}
