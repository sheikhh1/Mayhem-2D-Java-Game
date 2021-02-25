package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.entity.EntityType;
import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.layout.block.texture.BlockTexture;
import me.mayhem.game.level.layout.block.types.*;
import me.mayhem.game.level.spawning.SpawnPosition;
import me.mayhem.util.RGB;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ImageLevelGenerator implements LevelGenerator {

    private final int id;

    private Image levelImage;
    private Vector playerSpawnPosition;
    private Vector newCenter = null;

    private final List<Block> blocks = new ArrayList<>();
    private final List<SpawnPosition> enemySpawnPositions = new ArrayList<>();
    private final Map<RGB, BiConsumer<Integer, Integer>> colours = new HashMap<>();

    public ImageLevelGenerator(int id) {
        this.id = id;

        this.colours.put(RGB.of(255, 255, 255), (x, y) -> this.blocks.add(this.createBlock(x * 32,y * 32)));
        this.colours.put(RGB.of(0, 0, 255), (x, y) -> this.playerSpawnPosition = new Vector(x * 32, y * 32));
        this.colours.put(RGB.of(255, 0, 0), (x, y) -> this.enemySpawnPositions.add(new SpawnPosition(new Vector(x * 32, y * 32), EntityType.INFECTED.getSpawnMethod())));
        this.colours.put(RGB.of(225, 225, 225), (x, y) -> this.enemySpawnPositions.add(new SpawnPosition(new Vector(x * 32, y * 32), EntityType.FEROCIOUS.getSpawnMethod())));
        this.colours.put(RGB.of(215, 215, 215), (x, y) -> this.enemySpawnPositions.add(new SpawnPosition(new Vector(x * 32, y * 32), EntityType.CORROSIVE.getSpawnMethod())));
        this.colours.put(RGB.of(255, 128, 0), (x, y) -> this.enemySpawnPositions.add(new SpawnPosition(new Vector(x * 32, y * 32), EntityType.SPIKES.getSpawnMethod())));
        this.colours.put(RGB.of(200, 200, 200), (x, y) -> this.newCenter = new Vector(x * 32, y * 32));
        this.colours.put(RGB.of(0, 255, 255), (x, y) -> this.enemySpawnPositions.add(new SpawnPosition(new Vector(x * 32, y * 32), EntityType.DOOR.getSpawnMethod())));
        this.colours.put(RGB.of(0, 255, 0), (x, y) -> this.enemySpawnPositions.add(new SpawnPosition(new Vector(x * 32, y * 32), EntityType.KEY_CARD.getSpawnMethod())));
        this.colours.put(BlockTexture.BOUNCY.getRgb(), (x, y) -> this.blocks.add(this.createBouncyBlock(x * 32, y * 32)));
        this.colours.put(BlockTexture.LAVA.getRgb(), (x, y) -> this.blocks.add(this.createLavaBlock(x * 32, y * 32)));
        this.colours.put(BlockTexture.SPEED_UP_RIGHT.getRgb(), (x, y) -> this.blocks.add(this.createSpeedupRightBlock(x * 32, y * 32)));
        this.colours.put(BlockTexture.SPEED_UP_LEFT.getRgb(), (x, y) -> this.blocks.add(this.createSpeedupLeftBlock(x * 32, y * 32)));
        this.colours.put(BlockTexture.WALL_DAMAGE.getRgb(), (x, y) -> this.blocks.add(this.createWallDamageBlock(x * 32, y * 32)));
    }

    @Override
    public List<Block> generateLevel() {
        this.levelImage = UtilImageLoader.loadImageFromStream(getClass().getClassLoader().getResourceAsStream("levels/level-" + this.id + ".png"));
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

            if (this.playerSpawnPosition != null) {
                this.playerSpawnPosition.subtract(this.newCenter);
            }

            for (SpawnPosition enemySpawnPosition : this.enemySpawnPositions) {
                enemySpawnPosition.getPosition().subtract(this.newCenter);
            }
        }
    }

    public Vector getPlayerSpawnPosition() {
        return this.playerSpawnPosition;
    }

    public List<SpawnPosition> getEnemySpawnPositions() {
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
                .sprite(BlockTexture.BASIC.getSprite())
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
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private Block createLavaBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return LavaBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private Block createSpeedupRightBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return SpeedupRightBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private Block createSpeedupLeftBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return SpeedupLeftBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private Block createWallDamageBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return WallDamageBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }
}
