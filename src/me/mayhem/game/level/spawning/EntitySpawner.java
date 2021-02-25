package me.mayhem.game.level.spawning;

import me.mayhem.game.level.Level;
import me.mayhem.game.level.layout.ai.LevelGenerator;

public interface EntitySpawner {

    void spawnEntities(Level level, LevelGenerator generator);

}
