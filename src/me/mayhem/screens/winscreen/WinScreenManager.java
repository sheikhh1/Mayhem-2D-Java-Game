package me.mayhem.screens.winscreen;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.winscreen.items.WinContinueButton;
import me.mayhem.screens.winscreen.items.WinSaveButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class WinScreenManager implements ScreenManager {

    private final Sprite[] sprites = new Sprite[]{UtilSharedResources.getBackground()};
    private final Interactable[] buttons = new Interactable[]{
            createContinueButton(),
            createSaveButton()
    };

    private final Sound mainTheme;
    private final GameScreenManager previousGame;

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
    public void close(RenderWindow renderWindow) {

    }

    @Override
    public Sound getSound() {
        return mainTheme;
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
