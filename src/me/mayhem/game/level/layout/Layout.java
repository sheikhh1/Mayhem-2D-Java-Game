package me.mayhem.game.level.layout;

import me.mayhem.game.level.layout.ai.LevelGenerator;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;

import java.util.ArrayList;
import java.util.List;

public class Layout {

    private final List<Block> blocks = new ArrayList<>();

    public Layout(LevelGenerator generator) {
        this.blocks.addAll(generator.generateLevel());
    }

    public void draw(RenderWindow renderWindow) {
        for (Block block : this.blocks) {
            block.draw(renderWindow);
        }
    }

    public void moveBlocks(Vector direction) {
        for (Block block : this.blocks) {
            block.getPosition().add(direction);
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
