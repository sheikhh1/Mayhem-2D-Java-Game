package me.mayhem.screens.gamescreen;

import me.mayhem.game.GameManager;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.ScreenManager;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;


public class GameScreenManager implements ScreenManager {

    private GameManager game;
    private Difficulty difficulty;


    public GameScreenManager(RenderWindow window, Difficulty difficulty, String playerName) {
        this.difficulty = difficulty;
        this.game = new GameManager(window, this.difficulty, playerName);
        this.draw(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.game.initialize();
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        this.game.deinitialize();
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        this.game.draw();
        this.game.tick();
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }

    public GameManager getGame() {
        return this.game;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public Sound getSound() {
        return null;
    }

}
