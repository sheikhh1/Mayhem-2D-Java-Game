package me.mayhem.screens.game.game;

import me.mayhem.game.GameManager;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.save.SaveData;
import me.mayhem.screens.ScreenManager;
import me.mayhem.util.UtilSharedResources;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

/**
 * this method controls the screen that manages the gamemanager
 */
public class GameScreenManager implements ScreenManager {

    private final SaveData saveData;
    private final GameManager game;
    private final Sprite sprite;

    /**
     *
     * @param window the renderwindow that is to be drawn on
     * @param saveData the data that can be saved about a particular game
     */
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
        long startTime = System.currentTimeMillis();
        this.game.tick();
        long endTime = System.currentTimeMillis();
        System.out.println("Total tick duration: " + (endTime - startTime));
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
