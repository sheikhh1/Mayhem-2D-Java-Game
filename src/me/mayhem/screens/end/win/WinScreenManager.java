package me.mayhem.screens.end.win;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.end.win.items.WinContinueButton;
import me.mayhem.screens.end.win.items.WinSaveButton;
import me.mayhem.screens.game.game.GameScreenManager;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

/**
 * manager to manage the screen for when you win the level
 */
public class WinScreenManager implements ScreenManager {

    private final Sprite[] sprites = new Sprite[]{UtilSharedResources.getBackground()};
    private final Interactable[] buttons = new Interactable[]{
            this.createContinueButton(),
            this.createSaveButton()
    };

    private final Sound mainTheme;
    private final GameScreenManager previousGame;

    /**
     *
     * @param window the window that the screen is on
     * @param mainTheme the main themesound of the game
     * @param previousGame the previous screen manager for the game
     */
    public WinScreenManager(RenderWindow window, Sound mainTheme, GameScreenManager previousGame) {
        this.mainTheme = mainTheme;
        this.previousGame = previousGame;

        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.draw(renderWindow);
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interactable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Sprite sprite : sprites) {
            renderWindow.draw(sprite);
        }
        for (Interactable button : buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {}

    @Override
    public Sound getSound() {
        return this.mainTheme;
    }

    public WinContinueButton createContinueButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 8f) * 2);
        shape.setFillColor(new Color(176, 176, 176));

        return new WinContinueButton(shape);
    }

    public WinSaveButton createSaveButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 8f) * 5);
        shape.setFillColor(new Color(176, 176, 176));

        return new WinSaveButton(shape);
    }

    public GameScreenManager getPreviousGame() {
        return this.previousGame;
    }
}
