package me.mayhem.screens.menu.game.naming;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.menu.game.naming.items.InputBox;
import me.mayhem.screens.menu.game.naming.items.NameSelectContinueButton;
import me.mayhem.screens.menu.game.naming.items.ReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

/**
 *
 * This page allows you to set the name for the character and file
 *
 */
public class NameSelectScreen implements ScreenManager {

    private final Sound mainTheme;
    private final Difficulty difficulty;

    private final Sprite[] sprites = new Sprite[]{UtilSharedResources.getBackground()};
    private final Interactable[] buttons = new Interactable[]{
            this.createContinueButton(),
            this.createReturnButton(),
            this.createInput()
    };

    /**
     *
     * Default constructor taking the window to draw on and the main theme tune and the difficulty
     *
     * @param window Window to draw on
     * @param mainTheme The main song to play
     * @param difficulty The user selected difficulty
     */
    public NameSelectScreen(RenderWindow window, Sound mainTheme, Difficulty difficulty) {
        this.mainTheme = mainTheme;
        this.difficulty = difficulty;
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
     * Creates the input box, including size and position
     *
     * @return returns the input box
     */
    private InputBox createInput() {
        InputBox inputBox = new InputBox(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 6, (Mayhem.SCREEN_HEIGHT / 10f)).toVector());

        inputBox.setPosition(new Vector(200, 200));

        return inputBox;
    }

    /**
     *
     * Gets the name from the input box
     *
     * @param nameBox The input box to obtain the name from
     * @return The name from the input box
     */
    public String getName(InputBox nameBox) {
        return nameBox.getWritten();
    }

    /**
     *
     * The input box from the cache
     *
     * @return The input box being cached
     */
    public InputBox getInputBox() {
        return (InputBox) buttons[2];
    }

    /**
     * Creates the  name select button, including size and position
     *
     * @return returns the name select button
     */
    private NameSelectContinueButton createContinueButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 10f) * 8).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return new NameSelectContinueButton(shape);
    }

    /**
     * Creates the return button, including size and position
     *
     * @return returns the return button
     */
    private ReturnButton createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return new ReturnButton(shape);
    }

    /**
     *
     * Get the selected difficulty
     *
     * @return The user selected difficulty
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }
}
