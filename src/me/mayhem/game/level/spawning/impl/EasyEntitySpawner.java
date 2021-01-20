package me.mayhem.game.level.spawning.impl;

import me.mayhem.game.entity.enemies.infected.InfectedEnemy;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.util.Vector;

public class EasyEntitySpawner implements EntitySpawner {

    @Override
    public void spawnEntities(Level level) {
        //TODO:
        // create something that works based on the constraints of the editor

        for (Vector vector : level.getDifficulty().getGenerator().getEnemySpawnPosition()) {
            level.spawnEnemy(new InfectedEnemy(vector, level));
        }

    }
}
