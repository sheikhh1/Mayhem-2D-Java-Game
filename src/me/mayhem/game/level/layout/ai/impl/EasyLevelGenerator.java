package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.math.Vector;
import org.jsfml.graphics.Color;
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
        Vector position = new Vector(0, 450);
        int width = 640;
        int height = 30;

        return Block.builder()
                .fillColor(Color.RED)
                .drawable(new RectangleShape(new Vector2f(width, height)))
                .position(position)
                .width(width)
                .height(height)
                .build();
    }
}
