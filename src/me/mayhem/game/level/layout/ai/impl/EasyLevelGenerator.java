package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.layout.block.types.BouncyBlock;
import me.mayhem.util.RGB;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class EasyLevelGenerator implements LevelGenerator {

    private Image levelImage;
    private Vector playerSpawnPosition;
    private Vector keyCardSpawnPositon;
    private Vector doorPosition;
    private Vector newCenter = null;

    private final List<Block> blocks = new ArrayList<>();
    private final List<Vector> enemySpawnPositions = new ArrayList<>();
    private final Map<RGB, BiConsumer<Integer, Integer>> colours = new HashMap<>();

    public EasyLevelGenerator() {
        this.colours.put(RGB.of(255, 255, 255), (x, y) -> this.blocks.add(this.createBlock(x * 32,y * 32)));
        this.colours.put(RGB.of(0, 0, 255), (x, y) -> this.playerSpawnPosition = new Vector(x * 32, y * 32));
        this.colours.put(RGB.of(255, 0, 0), (x, y) -> this.enemySpawnPositions.add(new Vector(x * 32, y * 32)));
        this.colours.put(RGB.of(200, 200, 200), (x, y) -> this.newCenter = new Vector(x * 32, y * 32));
        this.colours.put(RGB.of(0, 255, 255), (x, y) -> this.doorPosition = new Vector(x * 32, y * 32));
        this.colours.put(RGB.of(0, 255, 0), (x, y) -> this.keyCardSpawnPositon = new Vector(x * 32, y * 32));
        this.colours.put(RGB.of(155, 155, 155), (x, y) -> this.blocks.add(this.createBouncyBlock(x * 32, y * 32)));
    }

    @Override
    public List<Block> generateLevel() {
        this.levelImage = UtilImageLoader.loadImageFromStream(getClass().getClassLoader().getResourceAsStream("levels/TEST-MAP.png"));
        this.loadLevel();

        return this.blocks;
    }

    /**
     * Method in charge of reading a .png file and deciphering the image pixel by pixel
     * Blocks are then drawn dependent on the color of pixels in the image
     * White Blocks = Terrain
     * Blue Block = Player Spawning Point
     */
    private void loadLevel() {
        this.blocks.clear();
        this.enemySpawnPositions.clear();

        BufferedImage bufferedLevel = this.levelImage.toBufferedImage();
        int levelWidth = bufferedLevel.getWidth();
        int levelHeight = bufferedLevel.getHeight();

        for (int x = 0; x < levelHeight; x++) {
            for (int y = 0; y < levelWidth; y++) {
                RGB rgb = RGB.from(bufferedLevel.getRGB(x, y));

                this.colours.getOrDefault(rgb, (a, b) -> {}).accept(x, y);
            }
        }

        if (this.newCenter != null) {
            for (Block block : this.blocks) {
                block.getPosition().subtract(this.newCenter);
            }

            this.keyCardSpawnPositon.subtract(this.newCenter);
            this.doorPosition.subtract(this.newCenter);
            this.playerSpawnPosition.subtract(this.newCenter);

            for (Vector enemySpawnPosition : this.enemySpawnPositions) {
                enemySpawnPosition.subtract(this.newCenter);
            }
        }
    }

    public Vector getPlayerSpawnPosition() {
        return this.playerSpawnPosition;
    }

    @Override
    public Vector getKeyCardSpawnPosition() {
        return this.keyCardSpawnPositon;
    }

    @Override
    public Vector getDoorPosition() {
        return this.doorPosition;
    }

    public List<Vector> getEnemySpawnPositions() {
        return this.enemySpawnPositions;
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

    private Block createBouncyBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return BouncyBlock.builder()
                .fillColor(Color.BLUE)
                .drawable(new RectangleShape(new Vector2f(width, height)))
                .position(position)
                .width(width)
                .height(height)
                .build();
    }
}
