package me.mayhem.game.level.layout.ai.impl;

import me.mayhem.game.entity.EntityType;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.game.level.layout.block.texture.BlockTexture;
import me.mayhem.game.level.layout.block.types.*;
import me.mayhem.game.level.spawning.SpawnPosition;
import me.mayhem.game.level.spawning.SpecialPosition;
import me.mayhem.util.RGB;
import me.mayhem.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ColourFactory {

    public static final String PLAYER_SPAWN = "player_spawn_pos";
    public static final String NEW_CENTER = "new_center_pos";

    private static final Map<RGB, BiFunction<Integer, Integer, Block>> BLOCK_COLOURS = new HashMap<>();
    private static final Map<RGB, BiFunction<Integer, Integer, SpecialPosition>> SPECIAL_COLOURS = new HashMap<>();
    private static final Map<RGB, BiFunction<Integer, Integer, SpawnPosition>> ENTITY_COLOURS = new HashMap<>();

    static {
        BLOCK_COLOURS.put(RGB.of(255, 255, 255), (x, y) -> createBlock(x * 32,y * 32));
        BLOCK_COLOURS.put(BlockTexture.BOUNCY.getRgb(), (x, y) -> createBouncyBlock(x * 32, y * 32));
        BLOCK_COLOURS.put(BlockTexture.LAVA.getRgb(), (x, y) -> createLavaBlock(x * 32, y * 32));
        BLOCK_COLOURS.put(BlockTexture.SPEED_UP_RIGHT.getRgb(), (x, y) -> createSpeedupRightBlock(x * 32, y * 32));
        BLOCK_COLOURS.put(BlockTexture.SPEED_UP_LEFT.getRgb(), (x, y) -> createSpeedupLeftBlock(x * 32, y * 32));
        BLOCK_COLOURS.put(BlockTexture.WALL_DAMAGE.getRgb(), (x, y) -> createWallDamageBlock(x * 32, y * 32));

        SPECIAL_COLOURS.put(RGB.of(0, 0, 255), (x, y) -> new SpecialPosition(PLAYER_SPAWN, new Vector(x * 32, y * 32)));
        SPECIAL_COLOURS.put(RGB.of(200, 200, 200), (x, y) -> new SpecialPosition(NEW_CENTER, new Vector(x * 32, y * 32)));

        ENTITY_COLOURS.put(RGB.of(255, 0, 0), (x, y) -> new SpawnPosition(new Vector(x * 32, y * 32), EntityType.INFECTED.getSpawnMethod()));
        ENTITY_COLOURS.put(RGB.of(225, 225, 225), (x, y) -> new SpawnPosition(new Vector(x * 32, y * 32), EntityType.FEROCIOUS.getSpawnMethod()));
        ENTITY_COLOURS.put(RGB.of(215, 215, 215), (x, y) -> new SpawnPosition(new Vector(x * 32, y * 32), EntityType.CORROSIVE.getSpawnMethod()));
        ENTITY_COLOURS.put(RGB.of(255, 128, 0), (x, y) -> new SpawnPosition(new Vector(x * 32, y * 32), EntityType.SPIKES.getSpawnMethod()));
        ENTITY_COLOURS.put(RGB.of(0, 255, 255), (x, y) -> new SpawnPosition(new Vector(x * 32, y * 32), EntityType.DOOR.getSpawnMethod()));
        ENTITY_COLOURS.put(RGB.of(0, 255, 0), (x, y) -> new SpawnPosition(new Vector(x * 32, y * 32), EntityType.KEY_CARD.getSpawnMethod()));
    }

    public static Block getBlock(RGB rgb, int x, int y) {
        BiFunction<Integer, Integer, Block> blockFunction = BLOCK_COLOURS.get(rgb);

        if (blockFunction == null) {
            return null;
        }

        return blockFunction.apply(x, y);
    }

    public static SpecialPosition getPosition(RGB rgb, int x, int y) {
        BiFunction<Integer, Integer, SpecialPosition> specialFunction = SPECIAL_COLOURS.get(rgb);

        if (specialFunction == null) {
            return null;
        }

        return specialFunction.apply(x, y);
    }

    public static SpawnPosition getSpawnPosition(RGB rgb, int x, int y) {
        BiFunction<Integer, Integer, SpawnPosition> spawnFunction = ENTITY_COLOURS.get(rgb);

        if (spawnFunction == null) {
            return null;
        }

        return spawnFunction.apply(x, y);
    }

    /**
     * Creates blocks at given positions
     * @param x - X position
     * @param y - Y Position
     * @return - Returns a new created block
     */
    private static Block createBlock(float x, float y) {
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

    private static Block createBouncyBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return BouncyBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private static Block createLavaBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return LavaBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private static Block createSpeedupRightBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return SpeedupRightBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private static Block createSpeedupLeftBlock(float x, float y) {
        Vector position = new Vector(x, y);
        int width = 31;
        int height = 31;

        return SpeedupLeftBlock.builder()
                .position(position)
                .width(width)
                .height(height)
                .build();
    }

    private static Block createWallDamageBlock(float x, float y) {
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
