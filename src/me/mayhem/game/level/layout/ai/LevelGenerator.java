package me.mayhem.game.level.layout.ai;

import me.mayhem.game.level.layout.block.Block;
import me.mayhem.math.Vector;

import java.util.List;

public interface LevelGenerator {

    List<Block> generateLevel();

    Vector getPlayerSpawnPosition();

}
