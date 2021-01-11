package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.math.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EasyLevelGenerator implements LevelGenerator {

    private Image levelImage= new Image();
    private LevelImageLoader loader = new LevelImageLoader();
    private List<Block> blocks = new ArrayList<>();

    @Override
    public List<Block> generateLevel() {

        levelImage = loader.loadLevel("src/me/mayhem/game/level/layout/leveldesign/test.png");

        loadLevel(levelImg);

        return blocks;
    }

    private void loadLevel(Image level) {
        BufferedImage level1 = level.toBufferedImage();

        int w = level1.getWidth();
        int h = level1.getHeight();

        System.out.println(w + " " + h);

        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                int pixel = level1.getRGB(x,y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 255 & blue == 255) {
                    blocks.add(createBlock(x*32,y*32));
                }
            }
        }
    }

    private Block createBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return Block.builder()
                .fillColor(Color.RED)
                .outlineColor(Color.GREEN)
                .drawable(new RectangleShape(new Vector2f(width, height)))
                .position(position)
                .width(width)
                .height(height)
                .build();
    }
}
