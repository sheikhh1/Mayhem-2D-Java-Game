package me.mayhem.screens.gamescreen;

import me.mayhem.game.GameManager;
import me.mayhem.screens.ScreenManager;
import org.jsfml.graphics.RenderWindow;

public class GameScreenManager implements ScreenManager {

    private GameManager game;

    public GameScreenManager(RenderWindow window) {
        game = new GameManager(window);
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
}
