package me.mayhem.screens.gamescreen;

import me.mayhem.game.GameManager;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.ScreenManager;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;


public class GameScreenManager implements ScreenManager {

    GameManager game;
    private Difficulty difficulty;

    public GameScreenManager(RenderWindow window, Difficulty difficulty,String playerName) {
        this.difficulty = difficulty;
        game = new GameManager(window, this.difficulty, playerName);
        this.draw(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {

    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {

    }

    @Override
    public void draw(RenderWindow renderWindow) {
        game.draw();
        game.tick();
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
