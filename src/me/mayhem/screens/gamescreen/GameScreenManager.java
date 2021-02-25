package me.mayhem.screens.gamescreen;

import me.mayhem.game.GameManager;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.ScreenManager;
import me.mayhem.util.UtilSharedResources;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;


public class GameScreenManager implements ScreenManager {

    private final int id;
    private final String playerName;
    private final GameManager game;
    private final Difficulty difficulty;
    private final Sprite sprite;

    public GameScreenManager(RenderWindow window, Difficulty difficulty, String playerName) {
        this(window, difficulty, 1, playerName);
    }

    public GameScreenManager(RenderWindow window, Difficulty difficulty, int id, String playerName) {
        this.id = id;
        this.playerName = playerName;
        this.difficulty = difficulty;
        this.game = new GameManager(window, id, this.difficulty, playerName);
        this.sprite = UtilSharedResources.getInGameBackground();
        this.draw(window);
    }

    public int getId() {
        return this.id;
    }

    public String getPlayerName() {
        return this.playerName;
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
        renderWindow.draw(this.sprite);
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
