package me.mayhem.game.level.spawning.impl;

import me.mayhem.game.entity.entities.collectable.key.KeyCard;
import me.mayhem.game.entity.entities.enemies.infected.InfectedEnemy;
import me.mayhem.game.entity.entities.friendly.door.Door;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.util.Vector;

public class SimpleEntitySpawner implements EntitySpawner {

    @Override
    public void spawnEntities(Level level) {
        for (Vector vector : level.getDifficulty().getGenerator().getEnemySpawnPositions()) {
            level.spawnEntity(new InfectedEnemy(vector.clone(), level));
        }

        level.spawnEntity(new KeyCard(level.getDifficulty().getGenerator().getKeyCardSpawnPosition()));

        level.spawnEntity(new Door(level.getDifficulty().getGenerator().getDoorPosition(), level));
    }
}
