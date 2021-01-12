package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class EasyLevelGenerator implements LevelGenerator {

    private Image levelImage = new Image();
    private List<Block> blocks = new ArrayList<>();
    private Vector playerSpawnPosition;

    @Override
    public List<Block> generateLevel() {
        this.levelImage = UtilImageLoader.loadImageFromStream(getClass().getResourceAsStream("levels/Level0.png"));
        this.loadLevel();

        return blocks;
    }

    /**
     * Method in charge of reading a .png file and deciphering the image pixel by pixel
     * Blocks are then drawn dependent on the color of pixels in the image
     * White Blocks = Terrain
     * Blue Block = Player Spawning Point
     */
    private void loadLevel() {

        BufferedImage bufferedLevel = this.levelImage.toBufferedImage();
        int levelWidth = bufferedLevel.getWidth();
        int levelHeight = bufferedLevel.getHeight();
        int red, green, blue, pixel;

        for (int x = 0; x < levelHeight; x++) {
            for (int y = 0; y < levelWidth; y++) {
                pixel = bufferedLevel.getRGB(x,y);
                red = (pixel >> 16) & 0xff;
                green = (pixel >> 8) & 0xff;
                blue = (pixel) & 0xff;

                if (red == 255 && green == 255 & blue == 255) {
                    blocks.add(createBlock(x*32,y*32));
                } else if (red == 0 && green == 0 && blue == 255) {
                    playerSpawnPosition = new Vector(x*32, y*32);
                }
            }
        }
    }

    public Vector getPlayerSpawnPosition() {
        return playerSpawnPosition;
    }

    /**
     * Creates blocks at given positions
     * @param x - X position
     * @param y - Y Position
     * @return - Returns a new created block
     */
    private Block createBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return Block.builder()
                .fillColor(Color.RED)
                .drawable(new RectangleShape(new Vector2f(width, height)))
                .position(position)
                .width(width)
                .height(height)
                .build();
    }
}
