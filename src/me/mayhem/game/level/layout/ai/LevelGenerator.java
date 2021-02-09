package me.mayhem.game.level.layout.ai;

import me.mayhem.game.level.layout.block.Block;
import me.mayhem.util.Vector;

import java.util.List;

public interface LevelGenerator {

    /**
     * Generates a level dependent on difficulty
     * @return A list of blocks read from the level designs (.png)
     */
    List<Block> generateLevel();

    /**
     * Player spawning position dependent on level
     * @return Player position as a vector
     */
    Vector getPlayerSpawnPosition();

    Vector getKeyCardSpawnPosition();

    Vector getDoorPosition();

    List<Vector> getEnemySpawnPositions();
    List<Vector> getObstacleSpawnPositions();

}
