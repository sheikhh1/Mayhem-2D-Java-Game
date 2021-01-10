package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.collision.impl.RectangleHitbox;
import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.math.Vector;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class EasyLevelGenerator implements LevelGenerator {

    @Override
    public List<Block> generateLevel() {
        List<Block> blocks = new ArrayList<>();

        blocks.add(this.createFloor());

        return blocks;
    }

    private Block createFloor() {
        Vector position = new Vector(0, 100);
        int width = 200;
        int height = 10;

        return new Block(position, new RectangleShape(new Vector2f(width, height)), new RectangleHitbox(position, height, width), color);
    }
}
