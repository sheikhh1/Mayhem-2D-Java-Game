package me.mayhem.game.level.layout;

import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.layout.block.Block;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;

import java.util.ArrayList;
import java.util.List;

public class Layout {

    private final List<Block> blocks = new ArrayList<>();

    public Layout(Difficulty difficulty) {
        this.blocks.addAll(difficulty.getGenerator().generateLevel());
    }

    public void draw(RenderWindow renderWindow) {
        for (Block block : this.blocks) {
            renderWindow.draw(block.getDrawable());
        }
    }

    public void moveBlocks(Vector direction) {
        for (Block block : this.blocks) {
            block.getPosition().add(direction);
            block.getDrawable().setPosition(block.getPosition().toVector());
        }
    }
}
