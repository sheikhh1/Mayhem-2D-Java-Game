package me.mayhem.game.level.layout.ai.impl;

import org.jsfml.graphics.Image;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Class to read and return an image given a specified file path
 */
public class LevelImageLoader {

    private Image testLevel = new Image();

    public Image loadLevel(Path path) {

        try {
            testLevel.loadFromFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testLevel;
    }
}
