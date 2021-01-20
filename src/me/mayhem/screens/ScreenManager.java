package me.mayhem.screens;

import me.mayhem.game.level.difficulty.Difficulty;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;

public interface ScreenManager {

    void loadScreen(RenderWindow renderWindow);

    void unloadScreen(RenderWindow renderWindow);

    void draw(RenderWindow renderWindow);

    void close(RenderWindow renderWindow);

    Difficulty getDifficulty();

    Sound getSound();

}
