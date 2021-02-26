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
 * a screen that allows you to input a name into the game for your character
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
     * @param window     the window that the game screen is to run on
     * @param mainTheme  the main theme of the game
     * @param difficulty the difficulty level that has be set by the previous screen
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
     * creates the input box that appears on the screen
     *
     * @return the box to be used on the screen
     */
    private InputBox createInput() {
        InputBox inputBox = new InputBox(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 6, (Mayhem.SCREEN_HEIGHT / 10f)).toVector());

        inputBox.setPosition(new Vector(200, 200));

        return inputBox;
    }

    public String getName(InputBox nameBox) {
        return nameBox.getWritten();
    }

    public InputBox getInputBox() {
        return (InputBox) buttons[2];
    }

    private NameSelectContinueButton createContinueButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 10f) * 8).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return new NameSelectContinueButton(shape);
    }

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
