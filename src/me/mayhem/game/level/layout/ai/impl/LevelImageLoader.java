package me.mayhem.game.level.layout.ai.impl;

import org.jsfml.graphics.Image;

import java.io.IOException;
import java.nio.file.Paths;

public class LevelImageLoader {

    private Image testLevel;

    public Image loadLevel(String path) {

        try {
            testLevel.loadFromFile(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testLevel;
    }
}
