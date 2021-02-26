package me.mayhem.screens.game.escape;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.game.escape.items.ReturnToGameButton;
import me.mayhem.screens.game.escape.items.ReturnToMainMenuButton;
import me.mayhem.screens.game.game.GameScreenManager;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class EscapeScreenManager implements ScreenManager {

    private final GameScreenManager prevScreen;
    private final Sound mainTheme;

    private Sprite[] sprites;
    private ButtonInteractable[] buttons;

    public EscapeScreenManager(RenderWindow window, Sound sound, GameScreenManager prev) {
        this.prevScreen = prev;
        this.mainTheme = sound;

        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createSprites();
        this.createButtons();
        this.draw(renderWindow);
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        if (this.mainTheme != null) {
            this.mainTheme.stop();
        }

        for (Interactable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Sprite sprite : this.sprites) {
            renderWindow.draw(sprite);
        }

        for (Interactable button : this.buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }

    @Override
    public Sound getSound() {
        return this.mainTheme;
    }

    /**
     * a method to create the sprites that appear in the game
     */
    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }

    /**
     * a method that creates the buttons that appear on the screen
     */
    private void createButtons() {
        ReturnToGameButton toGame = new ReturnToGameButton(createReturnButton());
        ReturnToMainMenuButton main = new ReturnToMainMenuButton(createQuitButton());

        buttons = new ButtonInteractable[]{toGame, main};
    }

    /**
     * a method that creates the button to return to the previous screen
     *
     * @return the shape that the button is created using
     */
    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 10f) * 2).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     * a method that creates the button to quit the game
     *
     * @return the shape that the button is created using
     */
    private Shape createQuitButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     * a method to get the previous gamescreen
     *
     * @return the previous gamescreen
     */
    public GameScreenManager getGameScreen() {
        return this.prevScreen;
    }
}
