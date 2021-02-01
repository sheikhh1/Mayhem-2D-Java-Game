package me.mayhem.screens.nameselectscreen;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.nameselectscreen.items.InputBox;
import me.mayhem.screens.nameselectscreen.items.NameSelectContinueButton;
import me.mayhem.screens.nameselectscreen.items.ReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class NameSelectScreen implements ScreenManager {

    private final Sound mainTheme;
    private final Difficulty difficulty;

    private Sprite[] sprites;
    private Interactable[] buttons;

    public NameSelectScreen(RenderWindow window, Sound mainTheme, Difficulty difficulty) {
        this.mainTheme = mainTheme;
        this.difficulty = difficulty;
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
        return mainTheme;
    }

    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }

    private void createButtons() {
        NameSelectContinueButton continueButton = new NameSelectContinueButton(createContinueButton());
        ReturnButton returnButton = new ReturnButton(createReturnButton());
        InputBox input = createInput();

        buttons = new Interactable[]{continueButton, returnButton, input};
    }

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

    private Shape createContinueButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 10f) * 8).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
