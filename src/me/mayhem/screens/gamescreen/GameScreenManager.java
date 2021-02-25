package me.mayhem.screens.gamescreen;

import me.mayhem.game.GameManager;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.save.SaveData;
import me.mayhem.screens.ScreenManager;
import me.mayhem.util.UtilSharedResources;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;


public class GameScreenManager implements ScreenManager {

    private final SaveData saveData;
    private final GameManager game;
    private final Sprite sprite;

    public GameScreenManager(RenderWindow window, SaveData saveData) {
        this.saveData = saveData;
        this.game = new GameManager(window, this.saveData);
        this.sprite = UtilSharedResources.getInGameBackground();
        this.draw(window);
    }

    public int getId() {
        return this.saveData.getId();
    }

    public String getPlayerName() {
        return this.saveData.getName();
    }

    public Difficulty getDifficulty() {
        return this.saveData.getDifficulty();
    }

    public SaveData getSaveData() {
        return this.saveData;
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

    @Override
    public Sound getSound() {
        return null;
    }

}
