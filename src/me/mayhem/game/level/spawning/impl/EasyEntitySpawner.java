package me.mayhem.game.level.spawning.impl;

import me.mayhem.game.entity.door.Door;
import me.mayhem.game.entity.enemies.infected.InfectedEnemy;
import me.mayhem.game.entity.keycard.KeyCard;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.util.Vector;

public class EasyEntitySpawner implements EntitySpawner {

    @Override
    public void spawnEntities(Level level) {
        //TODO:
        // create something that works based on the constraints of the editor

        for (Vector vector : level.getDifficulty().getGenerator().getEnemySpawnPositions()) {
            level.spawnEntity(new InfectedEnemy(vector.clone(), level));
        }

        level.spawnEntity(new KeyCard(level.getDifficulty().getGenerator().getKeyCardSpawnPosition()));

        level.spawnEntity(new Door(level.getDifficulty().getGenerator().getDoorPosition(), level));
    }
}
