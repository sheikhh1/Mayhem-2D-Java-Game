package me.mayhem.game.level.layout;

import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.game.level.layout.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Layout {

    private final List<Block> blocks = new ArrayList<>();

    public Layout(Difficulty difficulty) {
        this.blocks.addAll(difficulty.getGenerator().generateLevel());
    }

    //TODO:

}
