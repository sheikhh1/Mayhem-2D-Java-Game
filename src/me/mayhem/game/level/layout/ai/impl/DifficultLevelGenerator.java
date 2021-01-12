package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.util.math.Vector;

import java.util.Collections;
import java.util.List;

public class DifficultLevelGenerator implements LevelGenerator {
    @Override
    public List<Block> generateLevel() {
        return Collections.emptyList(); //TODO:
    }

    @Override
    public Vector getPlayerSpawnPosition() {
        return null;
    }
}
