package me.mayhem.game.level.layout.ai.impl;

import org.jsfml.graphics.Image;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LevelImageLoader {

    private Image testLevel;

    public Image loadLevel(Path path) {

        try {
            testLevel.loadFromFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testLevel;
    }
}
