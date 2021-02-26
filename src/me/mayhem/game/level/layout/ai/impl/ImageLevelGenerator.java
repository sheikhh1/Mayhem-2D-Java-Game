package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.spawning.SpawnPosition;
import me.mayhem.game.level.spawning.SpecialPosition;
import me.mayhem.util.RGB;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageLevelGenerator implements LevelGenerator {

    private final int id;

    private BufferedImage levelImage;
    private Vector playerSpawnPosition = null;
    private Vector newCenter = null;

    private final List<Block> blocks = new ArrayList<>();
    private final List<SpawnPosition> enemySpawnPositions = new ArrayList<>();

    public ImageLevelGenerator(int id) {
        this.id = id;
    }

    @Override
    public List<Block> generateLevel() {
        this.levelImage = UtilSharedResources.getLevelImage(this.id);
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

        int levelWidth = this.levelImage.getWidth();
        int levelHeight = this.levelImage.getHeight();

        for (int x = 0; x < levelHeight; x++) {
            for (int y = 0; y < levelWidth; y++) {
                RGB rgb = RGB.from(this.levelImage.getRGB(x, y));

                Block block = ColourFactory.getBlock(rgb, x, y);

                if (block != null) {
                    this.blocks.add(block);
                }

                SpecialPosition position = ColourFactory.getPosition(rgb, x, y);

                if (position != null) {
                    if (position.getId().equals(ColourFactory.PLAYER_SPAWN)) {
                        this.playerSpawnPosition = position.getPosition();
                    } else if (position.getId().equals(ColourFactory.NEW_CENTER)) {
                        this.newCenter = position.getPosition();
                    }
                }

                SpawnPosition spawnPosition = ColourFactory.getSpawnPosition(rgb, x, y);

                if (spawnPosition != null) {
                    this.enemySpawnPositions.add(spawnPosition);
                }
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
}
